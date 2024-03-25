package com.example.studentchat.chat.data

import android.util.Log
import com.example.studentchat.FirebaseApi
import com.example.studentchat.utils.TABLE_MESSAGES
import com.example.studentchat.utils.firebaseDatabase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class MessageApiImpl : MessageApi, FirebaseApi {
    private val messageDatabaseReference = firebaseDatabase.child(TABLE_MESSAGES)
    private var valueEventListener: ValueEventListener? = null
    private var childEventListener: ChildEventListener? = null

    override fun getAllMessage(conversationId: String): Flow<Message> = callbackFlow {
        childEventListener = messageDatabaseReference.child(conversationId)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val message = snapshot.getValue(Message::class.java)
                    message?.let {
                        it.timestamp = snapshot.key?.toLong() ?: 0
                        trySend(it)
                    }
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildRemoved(snapshot: DataSnapshot) {}

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onCancelled(error: DatabaseError) {
                    Log.e(javaClass.name, "OnCancelled", error.toException())
                }

            })
        awaitClose()
    }

    override suspend fun getMessage(conversationId: String, timestamp: Long): Message? {
        return messageDatabaseReference.child(conversationId)
            .child(timestamp.toString())
            .get()
            .await()
            .getValue(Message::class.java)
    }

    override suspend fun insertMessage(conversationId: String, message: Message) {
        messageDatabaseReference.child(conversationId)
            .child(message.timestamp.toString())
            .setValue(message)
    }

    override suspend fun deleteMessage(conversationId: String, message: Message) {
        messageDatabaseReference.child(conversationId)
            .child(message.timestamp.toString())
            .removeValue()
    }

    override fun removeListener() {
        valueEventListener?.let {
            messageDatabaseReference.removeEventListener(it)
        }
        childEventListener?.let {
            messageDatabaseReference.removeEventListener(it)
        }
    }

}