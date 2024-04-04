package com.example.data.remote

import com.example.data.remote.api.ConversationApi
import com.example.data.remote.model.ConversationRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class ConversationRemoteDataSource(
    private val conversationApi: ConversationApi,
) {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    fun getAllConversations(): Flow<List<ConversationRemote>> =
        conversationApi.getAllConversations()

    suspend fun getConversation(id: String): ConversationRemote? =
        withContext(ioDispatcher) {
            conversationApi.getConversation(id)
        }

    suspend fun insertConversation(conversationRemote: ConversationRemote) =
        withContext(ioDispatcher) {
            conversationApi.insertConversation(conversationRemote)
        }

    suspend fun updateConversation(conversationRemote: ConversationRemote) =
        withContext(ioDispatcher) {
            conversationApi.updateConversation(conversationRemote)
        }

    suspend fun deleteConversation(conversationRemote: ConversationRemote) =
        withContext(ioDispatcher) {
            conversationApi.deleteConversation(conversationRemote)
        }
}