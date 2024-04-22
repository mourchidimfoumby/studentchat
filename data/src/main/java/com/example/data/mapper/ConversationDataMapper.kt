package com.example.data.mapper

import com.example.data.local.dao.FriendsDao
import com.example.data.local.dao.MessageDao
import com.example.data.local.datastore.user.UserDataStore
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
) {

    suspend fun remoteToLocal(conversationRemote: ConversationRemote): ConversationEntity {
        val usersId = conversationRemote.interlocutors.keys
        val interlocutorId = usersId.find { friendsDao.getFriends(it) != null }!!
        return ConversationEntity(
            id = conversationRemote.id,
            interlocutorId = interlocutorId,
            lastMessageTimestamp = conversationRemote.lastMessageId.toLong()
        )
    }

    fun domainToLocal(conversation: Conversation): ConversationEntity =
        ConversationEntity(
            id = conversation.id,
            interlocutorId = conversation.interlocutor.uid,
            lastMessageTimestamp = conversation.lastMessage.timestamp
        )

    suspend fun localToRemote(conversationEntity: ConversationEntity): ConversationRemote {
        val interlocutorEntity = friendsDao.getFriends(conversationEntity.interlocutorId)
        val interlocutor = friendsDataMapper.localToDomain(interlocutorEntity!!)
        val user = userDataStore.getObject().first()
        return ConversationRemote(
            id = conversationEntity.id,
            interlocutors = mapOf(Pair(user.uid, true), Pair(interlocutor.uid, true)),
            lastMessageId = conversationEntity.lastMessageTimestamp.toString()
        )
    }

    suspend fun localToDomain(conversationEntity: ConversationEntity): Conversation {
        val interlocutorEntity = friendsDao.getFriends(conversationEntity.interlocutorId)!!
        val interlocutor = friendsDataMapper.localToDomain(interlocutorEntity)
        val messageEntity = messageDao.getMessage(conversationEntity.lastMessageTimestamp)!!
        val message = messageDataMapper.localToDomain(messageEntity)
        return Conversation(
            id = conversationEntity.id,
            interlocutor = interlocutor,
            lastMessage = message,
        )
    }
}