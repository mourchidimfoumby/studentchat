package com.example.data.local.datastore.user

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.local.datastore.DataStoreKey
import com.example.data.local.datastore.ObjectDataStore
import com.example.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

internal class UserDataStore(context: Context) : ObjectDataStore<User> {
    private val Context.dataStore by preferencesDataStore(
        name = "user_data_store"
    )
    private val dataSource = context.dataStore

    override fun getObject(): Flow<User> =
        dataSource.data.map { preferences ->
            User(
                preferences[DataStoreKey.User.UID]!!,
                preferences[DataStoreKey.User.FIRST_NAME]!!,
                preferences[DataStoreKey.User.LAST_NAME]!!,
                preferences[DataStoreKey.User.MAIL]!!,
                preferences[DataStoreKey.User.PASSWORD]!!,
                preferences[DataStoreKey.User.BIRTHDAY]!!
            )
        }.catch {
            Log.e(javaClass.name, it.message, it)
        }

    override suspend fun putObject(value: User) {
        dataSource.edit { preferences ->
            preferences[DataStoreKey.User.UID] = value.uid
            preferences[DataStoreKey.User.FIRST_NAME] = value.firstName
            preferences[DataStoreKey.User.LAST_NAME] = value.lastName
            preferences[DataStoreKey.User.MAIL] = value.mail
            preferences[DataStoreKey.User.PASSWORD] = value.password
            preferences[DataStoreKey.User.BIRTHDAY] = value.birthday
        }
    }

    override suspend fun <T> get(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataSource.data.map { preferences ->
            preferences[key] ?: defaultValue
        }

    override suspend fun <T> put(key: Preferences.Key<T>, value: T) {
        dataSource.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun delete() {
        dataSource.edit { preferences ->
            preferences.clear()
        }
    }
}