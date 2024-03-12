package com.example.studentchat.friends.data

import com.example.studentchat.Repository
import com.example.studentchat.user.data.User
import kotlinx.coroutines.flow.Flow

interface FriendsRepository: Repository {
    suspend fun getAllFriendsUid(): Flow<List<String>?>
    suspend fun addFriend(user: User)
    suspend fun removeFriend(friendId: String)
}