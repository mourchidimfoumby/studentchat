package com.example.data.local

import com.example.data.local.dao.FriendsDao
import com.example.data.local.entity.FriendsEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class FriendsLocalDataSource(
    private val friendsDao: FriendsDao,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getAllFriends(): Flow<List<FriendsEntity>> =
        friendsDao.getAllFriends()

    suspend fun getFriends(conversationId: String): FriendsEntity? =
        friendsDao.getFriends(conversationId)

    suspend fun insertFriends(conversationEntity: FriendsEntity) =
        withContext(coroutineDispatcher) {
            friendsDao.insertFriends(conversationEntity)
        }

    suspend fun updateFriends(conversationEntity: FriendsEntity) =
        withContext(coroutineDispatcher) {
            friendsDao.updateFriends(conversationEntity)
        }

    suspend fun deleteFriends(conversationEntity: FriendsEntity) =
        withContext(coroutineDispatcher) {
            friendsDao.deleteFriends(conversationEntity)
        }
}