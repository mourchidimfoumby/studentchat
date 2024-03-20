package com.example.studentchat.user.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val currentUser: Flow<User>
    fun getAllUser(): Flow<List<User>>
    suspend fun getUser(uid: String): User?
    suspend fun createUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteCurrentUser()
}