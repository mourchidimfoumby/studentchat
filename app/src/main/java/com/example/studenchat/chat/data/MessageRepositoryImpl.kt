package com.example.studenchat.chat.data

import android.util.Log
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.utils.TABLE_MESSAGES
import com.example.studenchat.utils.firebaseDatabase
import com.example.studenchat.utils.getValue
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MessageRepositoryImpl: MessageRepository {
    private val messageDatabaseReference = firebaseDatabase.child(TABLE_MESSAGES)
    private var valueEventListener: ValueEventListener? = null
    override suspend fun getAllMessage(conversation: Conversation): Flow<List<Message>?> = callbackFlow {
        messageDatabaseReference
            .child(conversation.id)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messageList = mutableListOf<Message>()
                    snapshot.children.forEach { values ->
                        val message = values.getValue(Message::class.java)
                        message?.let {
                            it.dateTime = values.key.toString()
                            messageList.add(it)
                        }
                    }
                    trySend(messageList.ifEmpty { null })
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, error.message)
                }
            })
        awaitClose {}
    }

    override suspend fun getLastMessage(conversation: Conversation): Flow<Message?> = callbackFlow {
        valueEventListener = messageDatabaseReference
            .child(conversation.id)
            .orderByKey()
            .limitToLast(1)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val message = snapshot.children.first().getValue(Message::class.java)
                        message?.let {
                            it.dateTime = snapshot.children.first().key.toString()
                        }
                        trySend(message)
                    } else trySend(null)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, error.message)
                }
            })
        awaitClose {}
    }

    override fun removeListener() {
        valueEventListener?.let {
            messageDatabaseReference.removeEventListener(it)
        }
    }

    override suspend fun getMessage(conversationId: String, messageId: String): Message? {
        val message = messageDatabaseReference
            .child(conversationId)
            .child(messageId)
            .getValue(Message::class.java)
        message?.let {
            it.dateTime = messageId
            return it
        }?: return null
    }

    override suspend fun deleteMessage(conversation: Conversation, message: Message) {
        messageDatabaseReference
            .child(conversation.id)
            .child(message.dateTime)
            .removeValue()
            .addOnFailureListener {
                Log.e(javaClass.name, "Failed to delete $message", it)
            }
    }

    override suspend fun createMessage(conversation: Conversation, message: Message) {
        messageDatabaseReference
            .child(conversation.id)
            .child(message.dateTime)
            .setValue(message)
    }

}