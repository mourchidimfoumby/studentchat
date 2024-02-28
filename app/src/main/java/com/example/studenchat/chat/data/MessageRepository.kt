package com.example.studenchat.chat.data

import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.Repository
import kotlinx.coroutines.flow.Flow

interface MessageRepository: Repository {
    suspend fun getAllMessage(conversation: Conversation): Flow<List<Message>?>
    suspend fun getMessage(conversationId: String, messageId: String): Message?
    suspend fun deleteMessage(conversation: Conversation, message: Message)
    suspend fun createMessage(conversation: Conversation, message: Message)

}