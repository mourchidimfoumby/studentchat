package com.example.data.remote

import com.example.data.remote.api.MessageApi
import com.example.data.remote.model.MessageRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MessageRemoteDataSource(
    private val messageApi: MessageApi
) {
    fun getAllMessage(conversationId: String): Flow<MessageRemote> =
        messageApi.getAllMessage(conversationId)

    suspend fun getMessage(conversationId: String, timestamp: Long): MessageRemote? =
        withContext(Dispatchers.IO) {
            messageApi.getMessage(conversationId, timestamp)
        }

    suspend fun insertMessage(conversationId: String, messageRemote: MessageRemote) =
        withContext(Dispatchers.IO) {
            messageApi.insertMessage(conversationId, messageRemote)
        }

    suspend fun deleteMessage(conversationId: String, messageRemote: MessageRemote) =
        withContext(Dispatchers.IO) {
            messageApi.deleteMessage(conversationId, messageRemote)
        }
}