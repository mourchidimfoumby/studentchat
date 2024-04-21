package com.example.data.repository

import com.example.data.model.Friends
import com.example.data.model.User
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {
    fun getAllFriends(): Flow<List<Friends>>
    fun getAllNoFriends(): Flow<List<User>>
    suspend fun getFriends(uid: String): Friends?
    suspend fun addFriends(friends: Friends)
    suspend fun deleteFriends(friends: Friends)
}