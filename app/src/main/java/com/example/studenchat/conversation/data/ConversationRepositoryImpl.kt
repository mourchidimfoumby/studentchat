package com.example.studenchat.conversation.data

import android.util.Log
import com.example.studenchat.Repository
import com.example.studenchat.user.data.User
import com.example.studenchat.utils.TABLE_CONVERSATIONS
import com.example.studenchat.utils.firebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ConversationRepositoryImpl: Repository {
    private val conversationDatabaseReference = firebaseDatabase.child(TABLE_CONVERSATIONS)
    private var valueEventListener: ValueEventListener? = null

    suspend fun getConversation(conversationId: String) = suspendCoroutine { continuation ->
        conversationDatabaseReference.child(conversationId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val result = snapshot.getValue(ConversationDTO::class.java)
                    result?.let {
                        it.id = conversationId
                        continuation.resume(result)
                    }?: run {
                        Log.w(javaClass.name, "Conversation $conversationId not found")
                        null
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, "Failed to get value of $conversationId", error.toException())
                }
            })
    }

    override fun closeListener() {
        valueEventListener?.let {
            conversationDatabaseReference.removeEventListener(it)
        }
    }

    suspend fun getConversation(listIds: List<String>): List<ConversationDTO>? {
        val conversationList = mutableListOf<ConversationDTO>()
        listIds.forEach { id ->
            getConversation(id).let { conversationList.add(it) }
        }
        return conversationList.ifEmpty { null }
    }
    suspend fun createConversation(conversation: Conversation) =
        conversationDatabaseReference
            .setValue(conversation)
            .addOnFailureListener {
                Log.e(javaClass.name, "Failed to create conversation : $conversation", it)
            }

    suspend fun removeUser(conversation: Conversation, user: User){
        conversationDatabaseReference
            .child(conversation.id)
            .child("interlocutors")
            .child(user.uid)
            .removeValue()
            .addOnFailureListener {
                Log.e(javaClass.name, "Failed to remove $user", it)
            }
    }
    suspend fun verifConversation(conversation: Conversation): Boolean{
        val snapshot = conversationDatabaseReference
            .child(conversation.id)
            .child("interlocutors")
            .get().await()
        return snapshot.hasChildren()
    }
    private suspend fun deleteConversation(conversation: Conversation) {
        conversationDatabaseReference
            .child(conversation.id)
            .removeValue()
    }
}