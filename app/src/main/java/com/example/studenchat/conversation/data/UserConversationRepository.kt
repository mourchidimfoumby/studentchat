package com.example.studenchat.conversation.data

import com.example.studenchat.Repository
import com.example.studenchat.conversation.data.Conversation
import kotlinx.coroutines.flow.Flow

interface UserConversationRepository: Repository {
//    suspend fun getAllIdsConversations(): Flow<List<String>>
    suspend fun getAllIdsConversations(): Flow<List<String>?>
    suspend fun createConversation(conversation: Conversation)
    suspend fun deleteConversation(conversation: Conversation)

}