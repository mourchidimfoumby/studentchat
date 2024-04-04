package com.example.data.repository

import com.example.data.model.Conversation
import com.example.data.remote.model.MessageRemote
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getAllMessage(conversation: Conversation): Flow<MessageRemote>
    suspend fun getMessage(conversationId: String, timestamp: Long): MessageRemote?
    suspend fun createMessage(conversation: Conversation, messageRemote: MessageRemote)
    suspend fun deleteMessage(conversation: Conversation, messageRemote: MessageRemote)
}