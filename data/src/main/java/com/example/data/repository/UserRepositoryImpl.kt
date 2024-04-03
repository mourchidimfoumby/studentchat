package com.example.data.repository

import com.example.data.model.User
import com.example.data.remote.UserRemoteDataSource
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.flow.Flow


class  UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override val currentUser: Flow<UserRemote> = userRemoteDataSource.getCurrentUser()

    override fun getAllUser(): Flow<List<UserRemote>> =
        userRemoteDataSource.getAllUser()

    override suspend fun getUser(uid: String): UserRemote? =
        userRemoteDataSource.getUser(uid)

    override suspend fun createUser(userRemote: UserRemote) {
        userRemoteDataSource.createUser(userRemote)
    }

    override suspend fun updateUser(userRemote: UserRemote) {
        userRemoteDataSource.updateUser(userRemote)
    }

    override suspend fun deleteCurrentUser() {
        userRemoteDataSource.deleteCurrentUser()
    }
}

