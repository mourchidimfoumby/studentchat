package com.example.data.remote

import com.example.data.model.DataEvent
import com.example.data.remote.api.MessageApi
import com.example.data.remote.model.MessageRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class MessageRemoteDataSource(
    private val messageApi: MessageApi
) {
    fun getAllMessage(conversationId: String): Flow<MessageRemote> =
        messageApi.getAllMessage(conversationId)

    suspend fun getMessage(conversationId: String, timestamp: Long): MessageRemote? =
        withContext(Dispatchers.IO) {
            messageApi.getMessage(conversationId, timestamp)
        }

    fun getLatestEvent(): Flow<DataEvent<MessageRemote>> =
        messageApi.getLatestEvent()

    suspend fun insertMessage(messageRemote: MessageRemote) =
        withContext(Dispatchers.IO) {
            messageApi.insertMessage(messageRemote)
        }

    suspend fun updateMessage(messageRemote: MessageRemote) =
        withContext(Dispatchers.IO) {
            messageApi.updateMessage(messageRemote)
        }

    suspend fun deleteMessage(messageRemote: MessageRemote) =
        withContext(Dispatchers.IO) {
            messageApi.deleteMessage(messageRemote)
        }
}