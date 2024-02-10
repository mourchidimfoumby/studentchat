package com.example.studenchat.data.repositories.firebase

import android.util.Log
import com.example.studenchat.data.sources.Conversation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ConversationRepository: FirebaseParameters {
    override val tableName: String = "user-conversations"
    suspend fun getAllConversations() : List<Conversation>{
        return suspendCoroutine{ continuation ->
            firebaseDatabase.child(tableName).child(userId!!).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val conversationsList = mutableListOf<Conversation>()
                    for (result in snapshot.children) {
                        val conversation = result.getValue(Conversation::class.java)
                        conversation?.let { conversationsList.add(it) }
                    }
                    continuation.resume(conversationsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(javaClass.name, error.message)
                }
            })
        }
    }

    suspend fun createConversation(conversation: Conversation) {
        suspendCoroutine{ continuation ->
            val key = firebaseDatabase.child("conversations").push().key
            if (key == null) {
                Log.w(this.javaClass.name, "Couldn't get push key for conversations")
                continuation.resume(Unit)
            }
            conversation.id = key
            val childUpdates = hashMapOf<String, Any>(
                "/conversation/$key" to conversation,
                "/$tableName/$userId/$key" to conversation
            )
            firebaseDatabase.updateChildren(childUpdates)
                .addOnFailureListener { error ->
                    Log.e(this.javaClass.name, error.message!!)
                }
        }
    }

    suspend fun deleteConversation(conversation: Conversation) {
        suspendCoroutine<Unit> {
            val childRemove = hashMapOf<String, Any?>(
                "/conversation/${conversation.id}" to null,
                "/$tableName/$userId/${conversation.id}" to null
            )
            firebaseDatabase.updateChildren(childRemove)
                .addOnFailureListener { error ->
                Log.e(this.javaClass.name, error.message!!)
            }
        }
    }


}