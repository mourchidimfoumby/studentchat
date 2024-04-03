package com.example.data.repository

import com.example.data.model.User
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val currentUser: Flow<UserRemote>
    fun getAllUser(): Flow<List<UserRemote>>
    suspend fun getUser(uid: String): UserRemote?
    suspend fun createUser(userRemote: UserRemote)
    suspend fun updateUser(userRemote: UserRemote)
    suspend fun deleteCurrentUser()
}