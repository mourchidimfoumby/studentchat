package com.example.studentchat.user.data

import kotlinx.coroutines.flow.Flow


class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override val currentUser: Flow<UserApiModel> = userRemoteDataSource.getCurrentUser()

    override fun getAllUser(): Flow<List<User>> =
        userRemoteDataSource.getAllUser()

    override suspend fun getUser(uid: String): User? =
        userRemoteDataSource.getUser(uid)

    override suspend fun createUser(userApiModel: UserApiModel) {
        userRemoteDataSource.createUser(userApiModel)
    }

    override suspend fun updateUser(userApiModel: UserApiModel) {
        userRemoteDataSource.updateUser(userApiModel)
    }

    override suspend fun deleteCurrentUser() {
        userRemoteDataSource.deleteCurrentUser()
    }

}

