package com.example.data.remote.api

import com.example.data.remote.model.FriendsRemote
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.flow.Flow

internal interface FriendsApi {
    fun getAllFriends(): Flow<List<FriendsRemote>>
    fun getAllNotFriends(): Flow<List<UserRemote>>
    suspend fun getFriends(uid: String): FriendsRemote?
    suspend fun insertFriends(userRemote: UserRemote)
    suspend fun deleteFriends(friendsRemote: FriendsRemote)
}