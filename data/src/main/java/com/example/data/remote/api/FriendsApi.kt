package com.example.data.remote.api

import com.example.data.model.DataEvent
import com.example.data.remote.model.FriendsRemote
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.flow.Flow

internal interface FriendsApi {
    fun getAllFriends(): Flow<List<FriendsRemote>>
    fun getAllNotFriends(): Flow<List<UserRemote>>
    suspend fun getFriends(uid: String): FriendsRemote?
    fun getLatestEvent(): Flow<DataEvent<FriendsRemote>>
    suspend fun insertFriends(friendsRemote: FriendsRemote)
    suspend fun deleteFriends(friendsRemote: FriendsRemote)
}