package com.example.data.remote

import com.example.data.model.DataEvent
import com.example.data.remote.api.FriendsApi
import com.example.data.remote.model.FriendsRemote
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.flow.Flow

internal class FriendsRemoteDataSource(
    private val friendsApi: FriendsApi
) {
    fun getAllFriends(): Flow<List<FriendsRemote>> =
        friendsApi.getAllFriends()

    fun getAllNotFriends(): Flow<List<UserRemote>> =
        friendsApi.getAllNotFriends()

    suspend fun getFriends(uid: String): FriendsRemote? =
        friendsApi.getFriends(uid)

    fun getLatestEvent(): Flow<DataEvent<FriendsRemote>> =
        friendsApi.getLatestEvent()

    suspend fun insertFriends(friendsRemote: FriendsRemote) {
        friendsApi.insertFriends(friendsRemote)
    }

    suspend fun deleteFriends(friendsRemote: FriendsRemote) {
        friendsApi.deleteFriends(friendsRemote)
    }
}