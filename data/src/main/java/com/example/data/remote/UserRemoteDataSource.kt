package com.example.data.remote

import com.example.data.remote.api.UserApi
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.flow.Flow

internal class UserRemoteDataSource(
    private val userApi: UserApi
) {
    fun getAllUser(): Flow<List<UserRemote>> = userApi.getAllUser()

    fun getCurrentUser(): Flow<UserRemote> = userApi.getCurrentUser()

    suspend fun getUser(uid: String): UserRemote? = userApi.getUser(uid)

    suspend fun createUser(userRemote: UserRemote) {
        userApi.insertUser(userRemote)
    }

    suspend fun updateUser(userRemote: UserRemote) {
        userApi.updateUser(userRemote)
    }

    suspend fun deleteCurrentUser() {
        userApi.deleteCurrentUser()
    }
}