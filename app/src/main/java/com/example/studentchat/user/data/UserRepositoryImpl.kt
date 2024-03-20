package com.example.studentchat.user.data

import kotlinx.coroutines.flow.Flow


class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override val currentUser: Flow<User> = userRemoteDataSource.getCurrentUser()

    override fun getAllUser(): Flow<List<User>> =
        userRemoteDataSource.getAllUser()

    override suspend fun getUser(uid: String): User? =
        userRemoteDataSource.getUser(uid)

    override suspend fun createUser(user: User) {
        userRemoteDataSource.createUser(user)
    }

    override suspend fun updateUser(user: User) {
        userRemoteDataSource.updateUser(user)
    }

    override suspend fun deleteCurrentUser() {
        userRemoteDataSource.deleteCurrentUser()
    }

}

