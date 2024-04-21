package com.example.data.remote.api

import com.example.data.model.DataEvent
import com.example.data.remote.model.ConversationRemote
import kotlinx.coroutines.flow.Flow

internal interface ConversationApi {
    fun getAllConversations(): Flow<List<ConversationRemote>>
    fun getLatestEvent(): Flow<DataEvent<ConversationRemote>>
    suspend fun getConversation(conversationId: String): ConversationRemote?
    suspend fun insertConversation(conversationRemote: ConversationRemote)
    suspend fun updateConversation(conversationRemote: ConversationRemote)
    suspend fun deleteConversation(conversationRemote: ConversationRemote)
}