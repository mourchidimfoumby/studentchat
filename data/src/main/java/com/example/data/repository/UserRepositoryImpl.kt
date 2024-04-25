package com.example.data.repository

import com.example.data.local.UserLocalDataSource
import com.example.data.mapper.UserDataMapper
import com.example.data.model.User
import com.example.data.remote.UserRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class  UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val userDataMapper: UserDataMapper
) : UserRepository {

    override val currentUser: Flow<User> =
        userLocalDataSource.getCurrentUser().map { userDataMapper.localToDomain(it) }

    override fun getAllUser(): Flow<List<User>> =
        userRemoteDataSource.getAllUser().map { userRemoteList ->
            userRemoteList.map { userDataMapper.remoteToDomain(it) }
        }

    override suspend fun getUser(uid: String): User? {
        val userRemote = userRemoteDataSource.getUser(uid)
        return userRemote?.let { userDataMapper.remoteToDomain(it) }
    }

    override suspend fun createUser(user: User) {
        val userLocal = userDataMapper.domainToLocal(user)
        val userRemote = userDataMapper.localToRemote(userLocal)
        userLocalDataSource.insertUser(userLocal)
        userRemoteDataSource.createUser(userRemote)
    }

    override suspend fun updateUser(user: User) {
        val userLocal = userDataMapper.domainToLocal(user)
        val userRemote = userDataMapper.localToRemote(userLocal)
        userLocalDataSource.updateUser(userLocal)
        userRemoteDataSource.updateUser(userRemote)
    }

    override suspend fun deleteCurrentUser() {
        userRemoteDataSource.deleteCurrentUser()
    }
}

