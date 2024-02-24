package com.example.studenchat.data.repository

import com.example.studenchat.data.source.Conversation
import kotlinx.coroutines.flow.Flow

interface UserConversationRepository: Repository {
//    suspend fun getAllIdsConversations(): Flow<List<String>>
    suspend fun getAllIdsConversations(): Flow<List<String>>
    suspend fun createConversation(conversation: Conversation)
    suspend fun deleteConversation(conversation: Conversation)

}