package com.example.studentchat.chat.data

import com.example.studentchat.Repository
import com.example.studentchat.conversation.data.Conversation
import kotlinx.coroutines.flow.Flow

interface MessageRepository: Repository {
    suspend fun getAllMessage(conversation: Conversation): Flow<List<Message>>
    suspend fun getLastMessage(conversation: Conversation): Flow<Message>
    suspend fun getMessage(conversationId: String, timestamp: Long): Message
    suspend fun deleteMessage(conversation: Conversation, message: Message)
    suspend fun createMessage(conversation: Conversation, message: Message)

}