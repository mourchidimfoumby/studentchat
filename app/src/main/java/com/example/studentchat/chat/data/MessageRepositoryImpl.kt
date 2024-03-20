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

class MessageRepositoryImpl(
    private val remoteMessageDataSource: MessageRemoteDataSource
): MessageRepository {

    override fun getAllMessage(conversation: Conversation): Flow<List<Message>> =
        remoteMessageDataSource.getAllMessage(conversation.id)

    override fun getLastMessage(conversation: Conversation): Flow<Message> =
        remoteMessageDataSource.getLastMessage(conversation.id)

    override suspend fun getMessage(conversationId: String, timestamp: Long): Message? =
        remoteMessageDataSource.getMessage(conversationId, timestamp)

    override suspend fun deleteMessage(conversation: Conversation, message: Message) {
        remoteMessageDataSource.deleteMessage(conversation.id, message)
    }

    override suspend fun createMessage(conversation: Conversation, message: Message) {
        remoteMessageDataSource.insertMessage(conversation.id, message)
    }


}