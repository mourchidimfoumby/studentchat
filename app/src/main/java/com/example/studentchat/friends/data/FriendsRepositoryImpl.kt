package com.example.studentchat.friends.data

import com.example.data.model.User
import kotlinx.coroutines.flow.Flow

class FriendsRepositoryImpl(
    private val friendsRemoteDataSource: FriendsRemoteDataSource
) : FriendsRepository {
    override val friends: Flow<List<Friends>> = friendsRemoteDataSource.getAllFriends()
    override val notFriends: Flow<List<User>> = friendsRemoteDataSource.getAllNotFriends()
    override suspend fun getFriends(uid: String): Friends? =
        friendsRemoteDataSource.getFriends(uid)

    override suspend fun addFriends(user: User) {
        friendsRemoteDataSource.addFriends(user)
    }

    override suspend fun deleteFriends(friends: Friends) {
        friendsRemoteDataSource.deleteFriends(friends)
    }
}