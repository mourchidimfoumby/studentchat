package com.example.studentchat.conversation.data

import com.example.studentchat.Repository
import kotlinx.coroutines.flow.Flow

class ConversationRepositoryImpl(
    private val conversationRemoteDataSource: ConversationRemoteDataSource
): ConversationRepository {

    override val conversations: Flow<List<Conversation>> =
        conversationRemoteDataSource.getAllConversations()
    override suspend fun createConversation(conversation: Conversation) {
        conversationRemoteDataSource.insertConversation(conversation)
    }

    override suspend fun getConversation(id: String): Conversation {
        return conversationRemoteDataSource.getConversation(id)
    }

    override suspend fun updateConversation(conversation: Conversation) {
        conversationRemoteDataSource.updateConversation(conversation)
    }

    override suspend fun deleteConversation(conversation: Conversation) {
        conversationRemoteDataSource.deleteConversation(conversation)
    }

}
