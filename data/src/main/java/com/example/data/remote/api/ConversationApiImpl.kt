package com.example.data.remote.api

import android.util.Log
import com.example.data.TABLE_CONVERSATIONS
import com.example.data.TABLE_USER_CONVERSATIONS
import com.example.data.firebaseDatabase
import com.example.data.remote.model.ConversationRemote
import com.example.data.userId
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

internal class ConversationApiImpl : ConversationApi, FirebaseApi {
    private var childEventListener: ChildEventListener? = null
    private var valueEventListener: ValueEventListener? = null
    private val conversationDatabaseReference =
        firebaseDatabase.child(TABLE_CONVERSATIONS)
    private val userConversationDatabaseReference =
        firebaseDatabase.child(TABLE_USER_CONVERSATIONS).child(userId)

    override fun getAllConversations(): Flow<List<ConversationRemote>> = callbackFlow {
        valueEventListener = userConversationDatabaseReference
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val jobs = snapshot.children.mapNotNull { child ->
                        val conversationId = child.key
                        conversationId?.let {
                            async {
                                getConversation(it)
                            }
                        }
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        trySend(jobs.mapNotNull { it.await() })
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, "Error getting conversations", error.toException())
                    close()
                }
            })
        awaitClose()
    }

    override suspend fun getConversation(conversationId: String): ConversationRemote? {
        val conversationRemote = conversationDatabaseReference.child(conversationId)
            .get()
            .await()
            .getValue(ConversationRemote::class.java)
        return conversationRemote?.apply {
            this.id = conversationId
        }
    }

    override suspend fun insertConversation(conversationRemote: ConversationRemote) {
        conversationDatabaseReference.child(conversationRemote.id).setValue(conversationRemote)
        userConversationDatabaseReference.child(userId).child(conversationRemote.id).setValue(true)
    }

    override suspend fun updateConversation(conversationRemote: ConversationRemote) {
        conversationDatabaseReference.child(conversationRemote.id).setValue(conversationRemote)
    }

    override suspend fun deleteConversation(conversationRemote: ConversationRemote) {
        userConversationDatabaseReference.child(userId).child(conversationRemote.id).removeValue()
        val conversation = conversationDatabaseReference.child(conversationRemote.id)
            .get()
            .await()
            .getValue(ConversationRemote::class.java)
        conversation?.let {
            if (it.isNotActive())
                conversationDatabaseReference.child(conversationRemote.id).removeValue()
        }
    }

    override fun removeListener() {
        childEventListener?.let {
            conversationDatabaseReference.removeEventListener(it)
            userConversationDatabaseReference.removeEventListener(it)
        }
        valueEventListener?.let {
            conversationDatabaseReference.removeEventListener(it)
            userConversationDatabaseReference.removeEventListener(it)
        }
    }
}