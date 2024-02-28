package com.example.studenchat.friends.data

import com.example.studenchat.Repository
import com.example.studenchat.user.data.User
import kotlinx.coroutines.flow.Flow

interface FriendsRepository: Repository {
    suspend fun getAllFriendsUid(): Flow<List<String>?>
    suspend fun addFriend(user: User)
    suspend fun removeFriend(friendId: String)
}