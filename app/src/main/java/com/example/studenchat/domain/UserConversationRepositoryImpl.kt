package com.example.studenchat.domain

import android.util.Log
import com.example.studenchat.data.repository.UserConversationRepository
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.utils.TABLE_USER_CONVERSATIONS
import com.example.studenchat.utils.firebaseDatabase
import com.example.studenchat.utils.userId
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserConversationRepositoryImpl: UserConversationRepository {
    private val userConversationsDatabaseReference = firebaseDatabase.child(TABLE_USER_CONVERSATIONS)
    private var valueEventListener: ValueEventListener? = null
    override suspend fun getAllIdsConversations(): Flow<List<String>> = callbackFlow {
        valueEventListener = userConversationsDatabaseReference.child(userId).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userConversationIds = snapshot.children.map { it.key }.toList()
                trySend(userConversationIds.filterNotNull())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(javaClass.name, error.message)
            }
        })
        awaitClose {
            Log.i(javaClass.name, "Fermeture du flow")
        }
    }

    override fun closeListener(){
        valueEventListener?.let {
            userConversationsDatabaseReference.removeEventListener(it)
        }
    }
//    override suspend fun getAllIdsConversations(): Flow<List<String>> = callbackFlow {
//        val listener = userConversationsDatabaseReference.child(userId).addValueEventListener(object: ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val userConversationIds = snapshot.children.map { it.key }.toList()
//                trySend(userConversationIds.filterNotNull())
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e(javaClass.name, error.message)
//                close()
//            }
//        })
//        awaitClose {
//            Log.e(javaClass.name, "Le channel est clos")
//            userConversationsDatabaseReference.removeEventListener(listener)
//        }
//    }
    override suspend fun createConversation(conversation: Conversation) {
        val key = userConversationsDatabaseReference.push().key
        if (key == null) {
            Log.w(this.javaClass.name, "Couldn't get push key for conversation")
        }
        conversation.id = key!!
        val childUpdates: Map<String, Any> = hashMapOf(
            "/$TABLE_USER_CONVERSATIONS/$userId/$key" to true,
            "/$TABLE_USER_CONVERSATIONS/${conversation.otherUser().uid}/$key" to true
        )
        firebaseDatabase.updateChildren(childUpdates)
            .addOnFailureListener { error ->
                Log.e(this.javaClass.name, error.message!!)
            }
    }

    override suspend fun deleteConversation(conversation: Conversation) {
        userConversationsDatabaseReference
            .child(userId)
            .child(conversation.id)
            .removeValue()
    }
}