package com.example.data.repository

import com.example.data.local.FriendsLocalDataSource
import com.example.data.mapper.FriendsDataMapper
import com.example.data.mapper.UserDataMapper
import com.example.data.model.DataEvent
import com.example.data.model.Friends
import com.example.data.model.User
import com.example.data.remote.FriendsRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class FriendsRepositoryImpl(
    private val friendsRemoteDataSource: FriendsRemoteDataSource,
    private val friendsLocalDataSource: FriendsLocalDataSource,
    private val friendsDataMapper: FriendsDataMapper,
    private val userDataMapper: UserDataMapper
) : FriendsRepository {
    init {
        CoroutineScope(Dispatchers.IO).launch { getLastDataEvent() }
    }

    override fun getAllFriends(): Flow<List<Friends>> =
        friendsLocalDataSource.getAllFriends().map { friendsEntityList ->
            friendsEntityList.map { friendsDataMapper.localToDomain(it) }
        }

    override fun getAllNotFriends(): Flow<List<User>> =
        friendsRemoteDataSource.getAllNotFriends().map { userRemoteList ->
            userRemoteList.map { userDataMapper.remoteToDomain(it) }
        }

    override suspend fun getFriends(uid: String): Friends? {
        val friendsEntity = friendsLocalDataSource.getFriends(uid)
        return friendsEntity?.let { friendsDataMapper.localToDomain(it) }
    }

    private suspend fun getLastDataEvent() {
        friendsRemoteDataSource.getLatestEvent().collect { dataEvent ->
            when (dataEvent) {
                is DataEvent.Add -> {
                    val data = friendsDataMapper.remoteToLocal(dataEvent.data)
                    friendsLocalDataSource.insertFriends(data)
                }

                is DataEvent.Modify -> {
                    val data = friendsDataMapper.remoteToLocal(dataEvent.data)
                    friendsLocalDataSource.updateFriends(data)
                }

                is DataEvent.Remove -> {
                    val data = friendsDataMapper.remoteToLocal(dataEvent.data)
                    friendsLocalDataSource.deleteFriends(data)
                }
            }
        }
    }

    override suspend fun addFriends(user: User) {
        val friends = userDataMapper.toFriends(user)
        val friendsEntity = friendsDataMapper.domainToLocal(friends)
        val friendsRemote = friendsDataMapper.localToRemote(friendsEntity)
        friendsLocalDataSource.insertFriends(friendsEntity)
        friendsRemoteDataSource.insertFriends(friendsRemote)
    }

    override suspend fun deleteFriends(friends: Friends) {
        val friendsEntity = friendsDataMapper.domainToLocal(friends)
        val friendsRemote = friendsDataMapper.localToRemote(friendsEntity)
        friendsLocalDataSource.deleteFriends(friendsEntity)
        friendsRemoteDataSource.deleteFriends(friendsRemote)
    }
}