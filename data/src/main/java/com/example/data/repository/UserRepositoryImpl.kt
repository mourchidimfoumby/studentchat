package com.example.data.repository

import com.example.data.model.User
import com.example.data.remote.UserRemoteDataSource
import kotlinx.coroutines.flow.Flow

internal class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun getCurrentUser(): Flow<User> = TODO()
//        userRemoteDataSource.getCurrentUser()

    override fun getAllUser(): Flow<List<User>> = TODO()
//        userRemoteDataSource.getAllUser()

    override suspend fun getUser(uid: String): User? = TODO()
//        userRemoteDataSource.getUser(uid)

    override suspend fun createUser(user: User) {
        TODO()
//        userRemoteDataSource.createUser(user)
    }

    override suspend fun updateUser(user: User) {
        TODO()
//        userRemoteDataSource.updateUser(user)
    }

    override suspend fun deleteCurrentUser() {
        userRemoteDataSource.deleteCurrentUser()
    }
}

