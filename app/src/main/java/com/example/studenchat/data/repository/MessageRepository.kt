package com.example.studenchat.data.repository

import com.example.studenchat.data.source.Conversation
import com.example.studenchat.data.source.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository: Repository {
    suspend fun getAllMessage(conversation: Conversation): Flow<List<Message>>
    suspend fun getMessage(messageId: String): Message?
    suspend fun deleteMessage(conversation: Conversation, message: Message)
    suspend fun createMessage(conversation: Conversation, message: Message)

}