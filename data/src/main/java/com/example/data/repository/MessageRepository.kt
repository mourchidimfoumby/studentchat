package com.example.data.repository

import com.example.data.remote.model.ConversationRemote
import com.example.data.remote.model.MessageRemote
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getAllMessage(conversationRemote: ConversationRemote): Flow<MessageRemote>
    suspend fun getMessage(conversationId: String, timestamp: Long): MessageRemote?
    suspend fun createMessage(conversationRemote: ConversationRemote, messageRemote: MessageRemote)
    suspend fun deleteMessage(conversationRemote: ConversationRemote, messageRemote: MessageRemote)
}