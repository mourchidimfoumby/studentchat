package com.example.data.remote

import com.example.data.remote.api.UserApi
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class UserRemoteDataSource(
    private val userApi: UserApi
) {
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun getAllUser(): Flow<List<UserRemote>> = userApi.getAllUser()

    fun getCurrentUser(): Flow<UserRemote> = userApi.getCurrentUser()

    suspend fun getUser(uid: String): UserRemote? =
        withContext(coroutineDispatcher) {
            userApi.getUser(uid)
        }

    suspend fun createUser(userRemote: UserRemote) =
        withContext(coroutineDispatcher) {
            userApi.insertUser(userRemote)
        }

    suspend fun updateUser(userRemote: UserRemote) =
        withContext(coroutineDispatcher) {
            userApi.updateUser(userRemote)
        }

    suspend fun deleteCurrentUser() =
        withContext(coroutineDispatcher) {
            userApi.deleteCurrentUser()
        }
}