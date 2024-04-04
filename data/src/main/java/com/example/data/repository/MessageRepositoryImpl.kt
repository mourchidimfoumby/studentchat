package com.example.data.repository

import com.example.data.model.Conversation
import com.example.data.remote.MessageRemoteDataSource
import com.example.data.remote.model.MessageRemote
import kotlinx.coroutines.flow.Flow

internal class MessageRepositoryImpl(
    private val remoteMessageDataSource: MessageRemoteDataSource
): MessageRepository {

    override fun getAllMessage(conversation: Conversation): Flow<MessageRemote> =
        remoteMessageDataSource.getAllMessage(conversation.id)

    override suspend fun getMessage(conversationId: String, timestamp: Long): MessageRemote? =
        remoteMessageDataSource.getMessage(conversationId, timestamp)

    override suspend fun deleteMessage(conversation: Conversation, messageRemote: MessageRemote) {
        remoteMessageDataSource.deleteMessage(conversation.id, messageRemote)
    }

    override suspend fun createMessage(conversation: Conversation, messageRemote: MessageRemote) {
        remoteMessageDataSource.insertMessage(conversation.id, messageRemote)
    }
}