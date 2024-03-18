package com.example.studentchat.conversation.data

import kotlinx.coroutines.flow.Flow

interface ConversationApi {
    fun getAllConversations(): Flow<List<Conversation?>>
    suspend fun getConversation(conversationId: String): Conversation?
    suspend fun insertConversation(conversation: Conversation)
    suspend fun updateConversation(conversation: Conversation)
    suspend fun deleteConversation(conversation: Conversation)
}