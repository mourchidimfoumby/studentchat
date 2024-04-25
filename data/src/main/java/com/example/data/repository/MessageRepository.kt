package com.example.data.repository

import com.example.data.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getAllMessage(conversationId: String): Flow<List<Message>>
    suspend fun getMessage(conversationId: String, timestamp: Long): Message?
    fun getLastMessage(conversationId: String): Flow<Message>
    suspend fun createMessage(message: Message)
    suspend fun updateMessage(message: Message)
    suspend fun deleteMessage(message: Message)
}