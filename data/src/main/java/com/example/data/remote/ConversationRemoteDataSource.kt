package com.example.data.remote

import com.example.data.model.DataEvent
import com.example.data.remote.api.ConversationApi
import com.example.data.remote.model.ConversationRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class ConversationRemoteDataSource(
    private val conversationApi: ConversationApi,
) {
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun getAllConversations(): Flow<List<ConversationRemote>> =
        conversationApi.getAllConversations()

    fun getLatestEvent(): Flow<DataEvent<ConversationRemote>> =
        conversationApi.getLatestEvent()

    suspend fun getConversation(id: String): ConversationRemote? =
        withContext(coroutineDispatcher) {
            conversationApi.getConversation(id)
        }

    suspend fun insertConversation(conversationRemote: ConversationRemote) =
        withContext(coroutineDispatcher) {
            conversationApi.insertConversation(conversationRemote)
        }

    suspend fun updateConversation(conversationRemote: ConversationRemote) =
        withContext(coroutineDispatcher) {
            conversationApi.updateConversation(conversationRemote)
        }

    suspend fun deleteConversation(conversationRemote: ConversationRemote) =
        withContext(coroutineDispatcher) {
            conversationApi.deleteConversation(conversationRemote)
        }
}