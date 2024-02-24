package com.example.studenchat.data.repository

import com.example.studenchat.data.source.User
import kotlinx.coroutines.flow.Flow

interface FriendsRepository: Repository {
    suspend fun getAllFriendsUid(): List<String>
    suspend fun addFriend(user: User)
    suspend fun removeFriend(friendId: String)
}