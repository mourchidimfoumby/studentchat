package com.example.data.remote.api

import android.util.Log
import com.example.data.TABLE_MESSAGE
import com.example.data.firebaseDatabase
import com.example.data.model.DataEvent
import com.example.data.remote.model.MessageRemote
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

internal class MessageApiImpl : MessageApi {
    private val messageDatabaseReference = firebaseDatabase.child(TABLE_MESSAGE)
    private var valueEventListener: ValueEventListener? = null
    private var childEventListener: ChildEventListener? = null

    override fun getAllMessage(conversationId: String): Flow<MessageRemote> = callbackFlow {
        childEventListener = messageDatabaseReference.child(conversationId)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val messageRemote = snapshot.getValue(MessageRemote::class.java)
                    messageRemote?.let {
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

    override suspend fun getMessage(conversationId: String, timestamp: Long): MessageRemote? {
        return messageDatabaseReference.child(conversationId)
            .child(timestamp.toString())
            .get()
            .await()
            .getValue(MessageRemote::class.java)
    }

    override fun getLatestEvent(): Flow<DataEvent<MessageRemote>> = callbackFlow {
        messageDatabaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                try {
                    val messageRemote = snapshot.getValue(MessageRemote::class.java)
                    trySend(DataEvent.Add(messageRemote!!))
                } catch (exception: Exception) {
                    Log.e(javaClass.name, "Error snapshot conversion", exception)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                try {
                    val messageRemote = snapshot.getValue(MessageRemote::class.java)
                    trySend(DataEvent.Modify(messageRemote!!))
                } catch (exception: Exception) {
                    Log.e(javaClass.name, "Error snapshot conversion", exception)
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                try {
                    val messageRemote = snapshot.getValue(MessageRemote::class.java)
                    trySend(DataEvent.Remove(messageRemote!!))
                } catch (exception: Exception) {
                    Log.e(javaClass.name, "Error snapshot conversion", exception)
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                val messageRemote = snapshot.getValue(MessageRemote::class.java)
                Log.i(javaClass.name, "$messageRemote moved")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(javaClass.name, "Error to get the latest event", error.toException())
            }

        })
        awaitClose()
    }

    override suspend fun insertMessage(messageRemote: MessageRemote) {
        messageDatabaseReference.child(messageRemote.conversationId)
            .child(messageRemote.timestamp.toString())
            .setValue(messageRemote)
    }

    override suspend fun updateMessage(messageRemote: MessageRemote) {
        messageDatabaseReference.child(messageRemote.conversationId)
            .child(messageRemote.timestamp.toString())
            .setValue(messageRemote)
    }

    override suspend fun deleteMessage(messageRemote: MessageRemote) {
        messageDatabaseReference.child(messageRemote.conversationId)
            .child(messageRemote.timestamp.toString())
            .removeValue()
    }

}