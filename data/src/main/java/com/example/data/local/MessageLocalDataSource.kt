package com.example.data.local

import com.example.data.local.dao.MessageDao
import com.example.data.local.entity.MessageEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class MessageLocalDataSource(
    private val messageDao: MessageDao,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getAllMessage(conversationId: String): Flow<List<MessageEntity>> =
        messageDao.getAllMessage(conversationId)

    suspend fun getMessage(conversationId: String, timestamp: Long): MessageEntity? =
        withContext(coroutineDispatcher) {
            messageDao.getMessage(conversationId, timestamp)
        }

    suspend fun insertMessage(messageEntity: MessageEntity) =
        withContext(coroutineDispatcher) {
            messageDao.insertMessage(messageEntity)
        }

    suspend fun updateMessage(messageEntity: MessageEntity) =
        withContext(coroutineDispatcher) {
            messageDao.updateMessage(messageEntity)
        }

    suspend fun deleteMessage(messageEntity: MessageEntity) =
        withContext(coroutineDispatcher) {
            messageDao.deleteMessage(messageEntity)
        }
}