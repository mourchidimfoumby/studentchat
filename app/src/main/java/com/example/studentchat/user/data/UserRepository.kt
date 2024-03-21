package com.example.studentchat.user.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val currentUser: Flow<UserApiModel>
    fun getAllUser(): Flow<List<User>>
    suspend fun getUser(uid: String): User?
    suspend fun createUser(userApiModel: UserApiModel)
    suspend fun updateUser(userApiModel: UserApiModel)
    suspend fun deleteCurrentUser()
}