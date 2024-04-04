package com.example.data.repository

import com.example.data.remote.MessageRemoteDataSource
import com.example.data.remote.model.ConversationRemote
import com.example.data.remote.model.MessageRemote
import kotlinx.coroutines.flow.Flow

internal class MessageRepositoryImpl(
    private val remoteMessageDataSource: MessageRemoteDataSource
): MessageRepository {

    override fun getAllMessage(conversationRemote: ConversationRemote): Flow<MessageRemote> =
        remoteMessageDataSource.getAllMessage(conversationRemote.id)

    override suspend fun getMessage(conversationId: String, timestamp: Long): MessageRemote? =
        remoteMessageDataSource.getMessage(conversationId, timestamp)

    override suspend fun deleteMessage(
        conversationRemote: ConversationRemote,
        messageRemote: MessageRemote
    ) {
        remoteMessageDataSource.deleteMessage(conversationRemote.id, messageRemote)
    }

    override suspend fun createMessage(
        conversationRemote: ConversationRemote,
        messageRemote: MessageRemote
    ) {
        remoteMessageDataSource.insertMessage(conversationRemote.id, messageRemote)
    }
}