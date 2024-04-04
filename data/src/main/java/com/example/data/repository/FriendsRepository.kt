package com.example.data.repository

import com.example.data.remote.model.FriendsRemote
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {
    val friends: Flow<List<FriendsRemote>>
    val notFriends: Flow<List<UserRemote>>
    suspend fun getFriends(uid: String): FriendsRemote?
    suspend fun addFriends(userRemote: UserRemote)
    suspend fun deleteFriends(friendsRemote: FriendsRemote)
}