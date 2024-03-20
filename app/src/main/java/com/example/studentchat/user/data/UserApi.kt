package com.example.studentchat.user.data

import kotlinx.coroutines.flow.Flow

interface UserApi {
    fun getAllUser(): Flow<List<User>>
    fun getCurrentUser(): Flow<User>
    suspend fun getUser(uid: String): User?
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteCurrentUser()
}