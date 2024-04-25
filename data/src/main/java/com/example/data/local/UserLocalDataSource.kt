package com.example.data.local

import com.example.data.local.datastore.DataStoreKey
import com.example.data.local.datastore.user.UserDataStore
import com.example.data.local.datastore.user.UserLocal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class UserLocalDataSource(
    private val userDataStore: UserDataStore,
) {
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun getCurrentUser(): Flow<UserLocal> = userDataStore.getObject()

    fun getFirstName(): Flow<String> =
        userDataStore.get(DataStoreKey.User.FIRST_NAME, "")

    fun getLastName(): Flow<String> =
        userDataStore.get(DataStoreKey.User.LAST_NAME, "")

    fun getMail(): Flow<String> =
        userDataStore.get(DataStoreKey.User.MAIL, "")

    fun getPassword(): Flow<String> =
        userDataStore.get(DataStoreKey.User.PASSWORD, "")

    fun getBirthday(): Flow<String> =
        userDataStore.get(DataStoreKey.User.BIRTHDAY, "")

    suspend fun insertUser(userLocal: UserLocal) =
        withContext(coroutineDispatcher) {
            userDataStore.putObject(userLocal)
        }

    suspend fun updateUser(userLocal: UserLocal) =
        withContext(coroutineDispatcher) {
            userDataStore.putObject(userLocal)
        }

    suspend fun deleteUser() =
        withContext(coroutineDispatcher) {
            userDataStore.delete()
        }
}