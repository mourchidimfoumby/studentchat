package com.example.data.repository

import com.example.data.model.Conversation
import com.example.data.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getAllMessage(conversation: Conversation): Flow<Message>
    suspend fun getMessage(conversationId: String, timestamp: Long): Message?
    suspend fun createMessage(conversation: Conversation, message: Message)
    suspend fun deleteMessage(conversation: Conversation, message: Message)
}