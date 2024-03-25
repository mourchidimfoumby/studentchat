package com.example.studentchat.chat.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MessageRemoteDataSource(
    private val messageApi: MessageApi
) {
    fun getAllMessage(conversationId: String): Flow<Message> =
        messageApi.getAllMessage(conversationId)

    suspend fun getMessage(conversationId: String, timestamp: Long): Message? =
        withContext(Dispatchers.IO) {
            messageApi.getMessage(conversationId, timestamp)
        }

    suspend fun insertMessage(conversationId: String, message: Message) =
        withContext(Dispatchers.IO) {
            messageApi.insertMessage(conversationId, message)
        }

    suspend fun deleteMessage(conversationId: String, message: Message) =
        withContext(Dispatchers.IO) {
            messageApi.deleteMessage(conversationId, message)
        }
}