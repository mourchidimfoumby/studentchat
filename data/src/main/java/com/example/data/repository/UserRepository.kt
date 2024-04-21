package com.example.data.repository

import com.example.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCurrentUser(): Flow<User>
    fun getAllUser(): Flow<List<User>>
    suspend fun getUser(uid: String): User?
    suspend fun createUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteCurrentUser()
}