package com.example.data.local

import com.example.data.local.dao.ConversationDao
import com.example.data.local.entity.ConversationEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class ConversationLocalDataSource(
    private val conversationDao: ConversationDao,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getAllConversation(): Flow<List<ConversationEntity>> =
        conversationDao.getAllConversation()

    fun getConversation(conversationId: String): ConversationEntity? =
        conversationDao.getConversation(conversationId)

    suspend fun insertConversation(conversationEntity: ConversationEntity) =
        withContext(coroutineDispatcher) {
            conversationDao.insertConversation(conversationEntity)
        }

    suspend fun updateConversation(conversationEntity: ConversationEntity) =
        withContext(coroutineDispatcher) {
            conversationDao.updateConversation(conversationEntity)
        }

    suspend fun deleteConversation(conversationEntity: ConversationEntity) =
        withContext(coroutineDispatcher) {
            conversationDao.deleteConversation(conversationEntity)
        }

}