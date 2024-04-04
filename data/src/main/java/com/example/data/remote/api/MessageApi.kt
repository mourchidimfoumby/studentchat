package com.example.data.remote.api

import com.example.data.remote.model.MessageRemote
import kotlinx.coroutines.flow.Flow

internal interface MessageApi {
    fun getAllMessage(conversationId: String): Flow<MessageRemote>
    suspend fun getMessage(conversationId: String, timestamp: Long): MessageRemote?
    suspend fun insertMessage(conversationId: String, messageRemote: MessageRemote)
    suspend fun deleteMessage(conversationId: String, messageRemote: MessageRemote)
}