package com.example.data.remote.api

import com.example.data.remote.model.ConversationRemote
import kotlinx.coroutines.flow.Flow

interface ConversationApi {
    fun getAllConversations(): Flow<List<ConversationRemote>>
    suspend fun getConversation(conversationId: String): ConversationRemote?
    suspend fun insertConversation(conversationRemote: ConversationRemote)
    suspend fun updateConversation(conversationRemote: ConversationRemote)
    suspend fun deleteConversation(conversationRemote: ConversationRemote)
}