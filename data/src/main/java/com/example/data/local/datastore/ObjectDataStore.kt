package com.example.data.local.datastore

import kotlinx.coroutines.flow.Flow

internal interface ObjectDataStore<T> : DataStore {
    fun getObject(): Flow<T>
    suspend fun putObject(value: T)
}