package com.example.studentchat.conversation.data

import kotlinx.coroutines.flow.Flow


interface ConversationRepository {
    val conversations: Flow<List<Conversation>>
    suspend fun getConversation(id: String): Conversation?
    suspend fun createConversation(conversation: Conversation)
    suspend fun updateConversation(conversation: Conversation)
    suspend fun deleteConversation(conversation: Conversation)
}