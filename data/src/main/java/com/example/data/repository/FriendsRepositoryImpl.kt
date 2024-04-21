package com.example.data.repository

import com.example.data.model.Friends
import com.example.data.model.User
import com.example.data.remote.FriendsRemoteDataSource
import kotlinx.coroutines.flow.Flow

internal class FriendsRepositoryImpl(
    private val friendsRemoteDataSource: FriendsRemoteDataSource,
) : FriendsRepository {
    override fun getAllFriends(): Flow<List<Friends>> = TODO()
//        friendsRemoteDataSource.getAllFriends()
    override fun getAllNoFriends(): Flow<List<User>> = TODO()
//        friendsRemoteDataSource.getAllNotFriends()

    override suspend fun getFriends(uid: String): Friends? = TODO()
//        friendsRemoteDataSource.getFriends(uid)

    override suspend fun addFriends(friends: Friends) {
        TODO()
//        friendsRemoteDataSource.addFriends(friends)
    }

    override suspend fun deleteFriends(friends: Friends) {
        TODO()
//        friendsRemoteDataSource.deleteFriends(friends)
    }
}