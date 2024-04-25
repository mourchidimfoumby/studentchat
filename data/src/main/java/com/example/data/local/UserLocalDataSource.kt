package com.example.data.local

import com.example.data.local.datastore.user.UserDataStore
import com.example.data.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class UserLocalDataSource(
    private val userDataStore: UserDataStore
) {
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun getCurrentUser(): Flow<User> = userDataStore.getObject()

    suspend fun insertCurrentUser(user: User) =
        withContext(coroutineDispatcher) {
            userDataStore.putObject(user)
        }

    suspend fun deleteCurrentUser() =
        withContext(coroutineDispatcher) {
            userDataStore.delete()
        }
}