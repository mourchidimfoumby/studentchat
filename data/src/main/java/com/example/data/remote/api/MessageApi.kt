package com.example.data.remote.api

import com.example.data.model.DataEvent
import com.example.data.remote.model.MessageRemote
import kotlinx.coroutines.flow.Flow

internal interface MessageApi {
    fun getAllMessage(conversationId: String): Flow<MessageRemote>
    suspend fun getMessage(conversationId: String, timestamp: Long): MessageRemote?
    fun getLatestEvent(): Flow<DataEvent<MessageRemote>>
    suspend fun insertMessage(messageRemote: MessageRemote)
    suspend fun updateMessage(messageRemote: MessageRemote)
    suspend fun deleteMessage(messageRemote: MessageRemote)
}