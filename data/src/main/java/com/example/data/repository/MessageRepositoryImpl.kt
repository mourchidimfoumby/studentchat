package com.example.data.repository

import com.example.data.local.MessageLocalDataSource
import com.example.data.mapper.MessageDataMapper
import com.example.data.model.Message
import com.example.data.remote.MessageRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class MessageRepositoryImpl(
    private val messageRemoteDataSource: MessageRemoteDataSource,
    private val messageLocalDataSource: MessageLocalDataSource,
    private val messageDataMapper: MessageDataMapper
) : MessageRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch { getLastDataEvent() }
    }
    override fun getAllMessage(conversationId: String): Flow<List<Message>> =
        messageLocalDataSource.getAllMessage(conversationId).map {  messageEntityList ->
            messageEntityList.map { messageDataMapper.localToDomain(it) }
        }

    override suspend fun getMessage(conversationId: String, timestamp: Long): Message? {
        val messageEntity = messageLocalDataSource.getMessage(conversationId, timestamp)
        return messageEntity?.let { messageDataMapper.localToDomain(it) }
    }

    private suspend fun getLastDataEvent() {
        messageRemoteDataSource.getLatestEvent().collect { dataEvent ->
            when (dataEvent) {
                is com.example.data.model.DataEvent.Add -> {
                    val data = messageDataMapper.remoteToLocal(dataEvent.data)
                    messageLocalDataSource.insertMessage(data)
                }

                is com.example.data.model.DataEvent.Modify -> {
                    val data = messageDataMapper.remoteToLocal(dataEvent.data)
                    messageLocalDataSource.updateMessage(data)
                }

                is com.example.data.model.DataEvent.Remove -> {
                    val data = messageDataMapper.remoteToLocal(dataEvent.data)
                    messageLocalDataSource.deleteMessage(data)
                }
            }
        }
    }

    override suspend fun createMessage(message: Message) {
        val messageEntity = messageDataMapper.domainToLocal(message)
        val messageRemote = messageDataMapper.localToRemote(messageEntity)
        messageLocalDataSource.insertMessage(messageEntity)
        messageRemoteDataSource.insertMessage(messageRemote)
    }

    override suspend fun updateMessage(message: Message) {
        val messageEntity = messageDataMapper.domainToLocal(message)
        val messageRemote = messageDataMapper.localToRemote(messageEntity)
        messageLocalDataSource.updateMessage(messageEntity)
        messageRemoteDataSource.updateMessage(messageRemote)
    }

    override suspend fun deleteMessage(message: Message) {
        val messageEntity = messageDataMapper.domainToLocal(message)
        val messageRemote = messageDataMapper.localToRemote(messageEntity)
        messageLocalDataSource.deleteMessage(messageEntity)
        messageRemoteDataSource.deleteMessage(messageRemote)
    }
}