package com.example.data.repository

import com.example.data.remote.ConversationRemoteDataSource
import com.example.data.remote.model.ConversationRemote
import kotlinx.coroutines.flow.Flow

internal class ConversationRepositoryImpl(
    private val conversationRemoteDataSource: ConversationRemoteDataSource
): ConversationRepository {

    override val conversations: Flow<List<ConversationRemote>> =
        conversationRemoteDataSource.getAllConversations()

    override suspend fun getConversation(id: String): ConversationRemote? =
        conversationRemoteDataSource.getConversation(id)

    override suspend fun createConversation(conversationRemote: ConversationRemote) {
        conversationRemoteDataSource.insertConversation(conversationRemote)
    }

    override suspend fun updateConversation(conversationRemote: ConversationRemote) {
        conversationRemoteDataSource.updateConversation(conversationRemote)
    }

    override suspend fun deleteConversation(conversationRemote: ConversationRemote) {
        conversationRemoteDataSource.deleteConversation(conversationRemote)
    }
}
