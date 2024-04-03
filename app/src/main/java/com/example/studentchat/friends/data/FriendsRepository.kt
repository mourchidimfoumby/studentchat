package com.example.studentchat.friends.data

import com.example.data.model.User
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {
    val friends: Flow<List<Friends>>
    val notFriends: Flow<List<User>>
    suspend fun getFriends(uid: String): Friends?
    suspend fun addFriends(user: User)
    suspend fun deleteFriends(friends: Friends)
}