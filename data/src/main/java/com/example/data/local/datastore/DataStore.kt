package com.example.data.local.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

internal interface DataStore {
    suspend fun <T> get(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> put(key: Preferences.Key<T>, value: T)
    suspend fun delete()
}