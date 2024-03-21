package com.example.studentchat.user.data

import kotlinx.coroutines.flow.Flow

interface UserApi {
    fun getAllUser(): Flow<List<User>>
    fun getCurrentUser(): Flow<UserApiModel>
    suspend fun getUser(uid: String): User?
    suspend fun insertUser(userApiModel: UserApiModel)
    suspend fun updateUser(userApiModel: UserApiModel)
    suspend fun deleteCurrentUser()
}