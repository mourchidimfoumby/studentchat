package com.example.studenchat.chat.data

import com.example.studenchat.Repository
import com.example.studenchat.conversation.data.Conversation
import kotlinx.coroutines.flow.Flow

interface MessageRepository: Repository {
    suspend fun getAllMessage(conversation: Conversation): Flow<List<Message>?>
    suspend fun getLastMessage(conversation: Conversation): Flow<Message?>
    suspend fun getMessage(conversationId: String, messageId: String): Message?
    suspend fun deleteMessage(conversation: Conversation, message: Message)
    suspend fun createMessage(conversation: Conversation, message: Message)

}