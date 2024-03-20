package com.example.studentchat.user.data

import kotlinx.coroutines.flow.Flow

class UserRemoteDataSource(
    private val userApi: UserApi
) {
    fun getAllUser(): Flow<List<User>> = userApi.getAllUser()

    fun getCurrentUser(): Flow<User> = userApi.getCurrentUser()

    suspend fun getUser(uid: String): User? = userApi.getUser(uid)

    suspend fun createUser(user: User) {
        userApi.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userApi.updateUser(user)
    }

    suspend fun deleteCurrentUser() {
        userApi.deleteCurrentUser()
    }
}