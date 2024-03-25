package com.example.studentchat.chat.data

import com.example.studentchat.conversation.data.Conversation
import kotlinx.coroutines.flow.Flow

class MessageRepositoryImpl(
    private val remoteMessageDataSource: MessageRemoteDataSource
): MessageRepository {

    override fun getAllMessage(conversation: Conversation): Flow<Message> =
        remoteMessageDataSource.getAllMessage(conversation.id)


    override suspend fun getMessage(conversationId: String, timestamp: Long): Message? =
        remoteMessageDataSource.getMessage(conversationId, timestamp)

    override suspend fun deleteMessage(conversation: Conversation, message: Message) {
        remoteMessageDataSource.deleteMessage(conversation.id, message)
    }

    override suspend fun createMessage(conversation: Conversation, message: Message) {
        remoteMessageDataSource.insertMessage(conversation.id, message)
    }


}