package com.example.data.mapper

import com.example.data.local.dao.FriendsDao
import com.example.data.local.dao.MessageDao
import com.example.data.local.datastore.implementation.UserDataStore
import com.example.data.local.entity.ConversationEntity
import com.example.data.model.Conversation
import com.example.data.remote.model.ConversationRemote
import kotlinx.coroutines.flow.first

internal class ConversationDataMapper(
    private val friendsDao: FriendsDao,
    private val friendsDataMapper: FriendsDataMapper,
    private val messageDao: MessageDao,
    private val messageDataMapper: MessageDataMapper,
    private val userDataStore: UserDataStore,
) : DataMapper<ConversationEntity, Conversation, ConversationRemote> {

    override suspend fun remoteToLocal(remote: ConversationRemote): ConversationEntity {
        val usersId = remote.interlocutors.keys
        val interlocutorId = usersId.find { friendsDao.getFriends(it) != null }!!
        return ConversationEntity(
            id = remote.id,
            interlocutorId = interlocutorId,
            lastMessageTimestamp = remote.lastMessageId.toLong()
        )
    }

    override suspend fun domainToLocal(domain: Conversation): ConversationEntity =
        ConversationEntity(
            id = domain.id,
            interlocutorId = domain.interlocutor.uid,
            lastMessageTimestamp = domain.lastMessage.timestamp
        )

    override suspend fun localToRemote(local: ConversationEntity): ConversationRemote {
        val interlocutorEntity = friendsDao.getFriends(local.interlocutorId)
        val interlocutor = friendsDataMapper.localToDomain(interlocutorEntity!!)
        val user = userDataStore.getObject().first()
        return ConversationRemote(
            id = local.id,
            interlocutors = mapOf(Pair(user.uid, true), Pair(interlocutor.uid, true)),
            lastMessageId = local.lastMessageTimestamp.toString()
        )
    }

    override suspend fun localToDomain(local: ConversationEntity): Conversation {
        val interlocutorEntity = friendsDao.getFriends(local.interlocutorId)!!
        val interlocutor = friendsDataMapper.localToDomain(interlocutorEntity)
        val messageEntity = messageDao.getMessage(local.lastMessageTimestamp)!!
        val message = messageDataMapper.localToDomain(messageEntity)
        return Conversation(
            id = local.id,
            interlocutor = interlocutor,
            lastMessage = message,
        )
    }
}