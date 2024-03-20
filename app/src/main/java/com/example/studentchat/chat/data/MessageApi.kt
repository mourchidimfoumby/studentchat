package com.example.studentchat.chat.data

import kotlinx.coroutines.flow.Flow

interface MessageApi {
    fun getAllMessage(conversationId: String): Flow<List<Message>>
    fun getLastMessage(conversationId: String): Flow<Message>
    suspend fun getMessage(conversationId: String, timestamp: Long): Message?
    suspend fun insertMessage(conversationId: String, message: Message)
    suspend fun deleteMessage(conversationId: String, message: Message)
}