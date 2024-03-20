package com.example.studentchat.chat.data

import com.example.studentchat.utils.firebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class MessageApiImpl: MessageApi {
    private var valueEventListener: ValueEventListener? = null
    override fun getAllMessage(conversationId: String): Flow<List<Message>> = callbackFlow {
        firebaseDatabase.child(conversationId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messageList = snapshot.children.mapNotNull { it.getValue(Message::class.java) }
                    trySend(messageList)
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
        awaitClose()
    }

    override fun getLastMessage(conversationId: String): Flow<Message> = callbackFlow {
        valueEventListener = firebaseDatabase.child(conversationId)
            .orderByChild("timestamp")
            .limitToLast(1)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val message = snapshot.children.firstOrNull()?.getValue(Message::class.java)
                    message?.let {
                        trySend(message)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
        awaitClose()
    }

    override suspend fun getMessage(conversationId: String, timestamp: Long): Message? {
        return firebaseDatabase.child(conversationId)
            .child(timestamp.toString())
            .get()
            .await()
            .getValue(Message::class.java)
    }

    override suspend fun insertMessage(conversationId: String, message: Message) {
        firebaseDatabase.child(conversationId).setValue(message)
    }

    override suspend fun deleteMessage(conversationId: String, message: Message) {
        firebaseDatabase.child(conversationId).child(message.timestamp.toString()).removeValue()
    }

}