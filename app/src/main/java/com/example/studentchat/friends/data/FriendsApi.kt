package com.example.studentchat.friends.data

import com.example.studentchat.user.data.User
import kotlinx.coroutines.flow.Flow

interface FriendsApi {
    fun getAllFriends(): Flow<List<Friends>>
    fun getAllNotFriends(): Flow<List<User>>
    suspend fun getFriends(uid: String): Friends?
    suspend fun insertFriends(user: User)
    suspend fun deleteFriends(friends: Friends)
}