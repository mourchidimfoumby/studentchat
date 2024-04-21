package com.example.data.repository

import com.example.data.model.Conversation
import com.example.data.model.Message
import com.example.data.remote.MessageRemoteDataSource
import kotlinx.coroutines.flow.Flow

internal class MessageRepositoryImpl(
    private val remoteMessageDataSource: MessageRemoteDataSource
): MessageRepository {

    override fun getAllMessage(conversation: Conversation): Flow<Message> = TODO()
//        remoteMessageDataSource.getAllMessage(conversation.id)

    override suspend fun getMessage(conversationId: String, timestamp: Long): Message? = TODO()
//        remoteMessageDataSource.getMessage(conversationId, timestamp)

    override suspend fun deleteMessage(conversation: Conversation, message: Message) {
//        remoteMessageDataSource.deleteMessage(conversation.id, message)
        TODO()
    }

    override suspend fun createMessage(conversation: Conversation, message: Message) {
        TODO()
//        remoteMessageDataSource.insertMessage(conversationRemote.id, messageRemote)
    }
}