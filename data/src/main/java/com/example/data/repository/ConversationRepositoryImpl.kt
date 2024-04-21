package com.example.data.repository

import com.example.data.local.ConversationLocalDataSource
import com.example.data.mapper.ConversationDataMapper
import com.example.data.model.Conversation
import com.example.data.model.DataEvent
import com.example.data.remote.ConversationRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class ConversationRepositoryImpl(
    private val conversationRemoteDataSource: ConversationRemoteDataSource,
    private val conversationLocalDataSource: ConversationLocalDataSource,
    private val conversationDataMapper: ConversationDataMapper
) : ConversationRepository {
    init {
        CoroutineScope(Dispatchers.IO).launch { getLatestDataEvent() }
    }

    override fun getAllConversations(): Flow<List<Conversation>> =
        conversationLocalDataSource.getAllConversation().map { conversationEntityList ->
            conversationEntityList.map { conversationDataMapper.localToDomain(it) }
        }

    private suspend fun getLatestDataEvent() {
        conversationRemoteDataSource.getLatestEvent().collect { dataEvent ->
            when (dataEvent) {
                is DataEvent.Add -> {
                    val data = conversationDataMapper.remoteToLocal(dataEvent.data)
                    conversationLocalDataSource.insertConversation(data)
                }

                is DataEvent.Modify -> {
                    val data = conversationDataMapper.remoteToLocal(dataEvent.data)
                    conversationLocalDataSource.updateConversation(data)
                }

                is DataEvent.Remove -> {
                    val data = conversationDataMapper.remoteToLocal(dataEvent.data)
                    conversationLocalDataSource.deleteConversation(data)
                }
            }
        }
    }

    override suspend fun getConversation(id: String): Conversation? =
        conversationLocalDataSource.getConversation(id)?.let {
            conversationDataMapper.localToDomain(it)
        }

    override suspend fun createConversation(conversation: Conversation) {
        val conversationEntity = conversationDataMapper.domainToLocal(conversation)
        val conversationRemote = conversationDataMapper.localToRemote(conversationEntity)
        conversationLocalDataSource.insertConversation(conversationEntity)
        conversationRemoteDataSource.insertConversation(conversationRemote)
    }

    override suspend fun updateConversation(conversation: Conversation) {
        val conversationEntity = conversationDataMapper.domainToLocal(conversation)
        val conversationRemote = conversationDataMapper.localToRemote(conversationEntity)
        conversationLocalDataSource.updateConversation(conversationEntity)
        conversationRemoteDataSource.updateConversation(conversationRemote)
    }

    override suspend fun deleteConversation(conversation: Conversation) {
        val conversationEntity = conversationDataMapper.domainToLocal(conversation)
        val conversationRemote = conversationDataMapper.localToRemote(conversationEntity)
        conversationLocalDataSource.deleteConversation(conversationEntity)
        conversationRemoteDataSource.deleteConversation(conversationRemote)
    }
}
