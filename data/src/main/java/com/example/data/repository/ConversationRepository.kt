package com.example.data.repository

import com.example.data.remote.model.ConversationRemote
import kotlinx.coroutines.flow.Flow


interface ConversationRepository {
    val conversations: Flow<List<ConversationRemote>>
    suspend fun getConversation(id: String): ConversationRemote?
    suspend fun createConversation(conversationRemote: ConversationRemote)
    suspend fun updateConversation(conversationRemote: ConversationRemote)
    suspend fun deleteConversation(conversationRemote: ConversationRemote)
}