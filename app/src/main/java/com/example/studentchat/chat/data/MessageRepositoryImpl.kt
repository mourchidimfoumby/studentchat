package com.example.studentchat.chat.data

import android.util.Log
import com.example.studentchat.conversation.data.Conversation
import com.example.studentchat.utils.TABLE_MESSAGES
import com.example.studentchat.utils.firebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class MessageRepositoryImpl: MessageRepository {
    private val messageDatabaseReference = firebaseDatabase.child(TABLE_MESSAGES)
    private var valueEventListener: ValueEventListener? = null

    override suspend fun getAllMessage(conversation: Conversation): Flow<List<Message>> = callbackFlow {
        messageDatabaseReference
            .child(conversation.id)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messageList = mutableListOf<Message>()
                    snapshot.children.forEach { values ->
                        val message = values.getValue(Message::class.java)
                        message?.let {
                            messageList.add(it)
                        }
                    }
                    trySend(messageList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, error.message)
                }
            })
        awaitClose {}
    }

    override suspend fun getLastMessage(conversation: Conversation): Flow<Message> = callbackFlow {
        valueEventListener = messageDatabaseReference.child(conversation.id)
            .orderByKey()
            .limitToLast(1)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val message = snapshot.children.first().getValue(Message::class.java)
                    trySend(message!!)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, error.message)
                }
            })
        awaitClose {}
    }

    fun removeListener() {
        valueEventListener?.let {
            messageDatabaseReference.removeEventListener(it)
        }
    }

    override suspend fun getMessage(conversationId: String, timestamp: Long): Message {
        val message = messageDatabaseReference
            .child(conversationId)
            .child(timestamp.toString())
            .get()
            .await()
            .getValue(Message::class.java)
        return message ?: Message()
    }

    override suspend fun deleteMessage(conversation: Conversation, message: Message) {
        messageDatabaseReference
            .child(conversation.id)
            .child(message.datetime.toString())
            .removeValue()
            .addOnFailureListener {
                Log.e(javaClass.name, "Failed to delete $message", it)
            }
    }

    override suspend fun createMessage(conversation: Conversation, message: Message) {
        messageDatabaseReference
            .child(conversation.id)
            .child(message.datetime.toString())
            .setValue(message)
    }

}