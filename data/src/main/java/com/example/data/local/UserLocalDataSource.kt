package com.example.data.local

import com.example.data.local.datastore.user.UserDataStore
import com.example.data.model.User
import kotlinx.coroutines.flow.Flow

internal class UserLocalDataSource(
    private val userDataStore: UserDataStore
) {
    fun getCurrentUser(): Flow<User> = userDataStore.getObject()

    suspend fun insertCurrentUser(user: User) {
        userDataStore.putObject(user)
    }

    suspend fun deleteCurrentUser() {
        userDataStore.delete()
    }
}