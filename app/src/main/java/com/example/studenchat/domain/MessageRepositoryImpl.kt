package com.example.studenchat.domain

import android.util.Log
import com.example.studenchat.data.repository.MessageRepository
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.data.source.Message
import com.example.studenchat.utils.TABLE_MESSAGES
import com.example.studenchat.utils.firebaseDatabase
import com.example.studenchat.utils.getValue
import com.example.studenchat.utils.remove
import com.example.studenchat.utils.userId
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MessageRepositoryImpl: MessageRepository {
    private val messageDatabaseReference = firebaseDatabase.child(TABLE_MESSAGES)
    private var valueEventListener: ValueEventListener? = null
    override suspend fun getAllMessage(conversation: Conversation): Flow<List<Message>> = callbackFlow {
        valueEventListener = messageDatabaseReference
            .child(conversation.id)
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messageList = mutableListOf<Message>()
                    snapshot.children.forEach { values ->
                        val message = values.getValue(Message::class.java)
                        message?.let {
                            it.dateTime = values.key.toString()
                            messageList.add(it)
                        }
                    }
                    trySend(messageList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, error.message)
                }
            })
        awaitClose {
            Log.i(javaClass.name, "Flow closed")
        }
    }

    override suspend fun getMessage(messageId: String): Message? {
        return messageDatabaseReference
            .child(messageId)
            .getValue(Message::class.java)
    }

    override suspend fun deleteMessage(conversation: Conversation, message: Message) {
        messageDatabaseReference
            .child(conversation.id)
            .child(message.dateTime.toString())
            .remove()
    }

    override suspend fun createMessage(conversation: Conversation, message: Message) {
        messageDatabaseReference
            .child(conversation.id)
            .child(message.dateTime.toString())
            .setValue(message)
    }

    override fun closeListener() {
        valueEventListener?.let {
            messageDatabaseReference.removeEventListener(it)
        }
    }
}