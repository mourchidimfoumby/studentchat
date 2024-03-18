package com.example.studentchat.conversation.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ConversationRemoteDataSource(
    private val conversationApi: ConversationApi,
) {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    fun getAllConversations(): Flow<List<Conversation>> =
        conversationApi.getAllConversations()

    suspend fun getConversation(id: String): Conversation =
        withContext(ioDispatcher) {
            conversationApi.getConversation(id)
        }

    suspend fun insertConversation(conversation: Conversation) =
        withContext(ioDispatcher) {
            conversationApi.insertConversation(conversation)
        }

    suspend fun updateConversation(conversation: Conversation) =
        withContext(ioDispatcher) {
            conversationApi.updateConversation(conversation)
        }

    suspend fun deleteConversation(conversation: Conversation) =
        withContext(ioDispatcher) {
            conversationApi.deleteConversation(conversation)
        }
}