package com.example.data.remote.api

import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.flow.Flow

internal interface UserApi {
    fun getAllUser(): Flow<List<UserRemote>>
    fun getCurrentUser(): Flow<UserRemote>
    suspend fun getUser(uid: String): UserRemote?
    suspend fun insertUser(userApiModel: UserRemote)
    suspend fun updateUser(userApiModel: UserRemote)
    suspend fun deleteCurrentUser()
}