package com.example.data.repository

import com.example.data.remote.FriendsRemoteDataSource
import com.example.data.remote.model.FriendsRemote
import com.example.data.remote.model.UserRemote
import kotlinx.coroutines.flow.Flow

internal class FriendsRepositoryImpl(
    private val friendsRemoteDataSource: FriendsRemoteDataSource
) : FriendsRepository {
    override val friends: Flow<List<FriendsRemote>> = friendsRemoteDataSource.getAllFriends()
    override val notFriends: Flow<List<UserRemote>> = friendsRemoteDataSource.getAllNotFriends()

    override suspend fun getFriends(uid: String): FriendsRemote? =
        friendsRemoteDataSource.getFriends(uid)

    override suspend fun addFriends(friendsRemote: FriendsRemote) {
        friendsRemoteDataSource.addFriends(friendsRemote)
    }

    override suspend fun deleteFriends(friendsRemote: FriendsRemote) {
        friendsRemoteDataSource.deleteFriends(friendsRemote)
    }
}