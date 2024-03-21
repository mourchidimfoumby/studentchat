package com.example.studentchat.user.data

import kotlinx.coroutines.flow.Flow

class UserRemoteDataSource(
    private val userApi: UserApi
) {
    fun getAllUser(): Flow<List<User>> = userApi.getAllUser()

    fun getCurrentUser(): Flow<UserApiModel> = userApi.getCurrentUser()

    suspend fun getUser(uid: String): User? = userApi.getUser(uid)

    suspend fun createUser(userApiModel: UserApiModel) {
        userApi.insertUser(userApiModel)
    }

    suspend fun updateUser(userApiModel: UserApiModel) {
        userApi.updateUser(userApiModel)
    }

    suspend fun deleteCurrentUser() {
        userApi.deleteCurrentUser()
    }
}