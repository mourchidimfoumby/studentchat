package com.example.data.remote

import com.example.data.model.DataEvent
import com.example.data.remote.api.FriendsApi
import com.example.data.remote.model.FriendsRemote
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class FriendsRemoteDataSource(
    private val friendsApi: FriendsApi
) {
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
    fun getAllFriends(): Flow<List<FriendsRemote>> =
        friendsApi.getAllFriends()

    fun getAllNotFriends(): Flow<List<UserRemote>> =
        friendsApi.getAllNotFriends()

    suspend fun getFriends(uid: String): FriendsRemote? =
        withContext(coroutineDispatcher) {
            friendsApi.getFriends(uid)
        }

    fun getLatestEvent(): Flow<DataEvent<FriendsRemote>> =
        friendsApi.getLatestEvent()

    suspend fun insertFriends(friendsRemote: FriendsRemote) =
        withContext(coroutineDispatcher) {
            friendsApi.insertFriends(friendsRemote)
        }

    suspend fun deleteFriends(friendsRemote: FriendsRemote) =
        withContext(coroutineDispatcher) {
            friendsApi.deleteFriends(friendsRemote)
        }
}