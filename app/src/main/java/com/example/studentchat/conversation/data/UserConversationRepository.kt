package com.example.studentchat.conversation.data

import com.example.studentchat.Repository
import kotlinx.coroutines.flow.Flow

interface UserConversationRepository: Repository {
//    suspend fun getAllIdsConversations(): Flow<List<String>>
    suspend fun getAllIdsConversations(): Flow<List<String>?>
    suspend fun createConversation(conversation: Conversation)
    suspend fun deleteConversation(conversation: Conversation)

}