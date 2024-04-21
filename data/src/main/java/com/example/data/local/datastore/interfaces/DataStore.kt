package com.example.data.local.datastore.interfaces

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

internal sealed interface DataStore {
    suspend fun <T> get(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> put(key: Preferences.Key<T>, value: T)
    suspend fun delete()
}