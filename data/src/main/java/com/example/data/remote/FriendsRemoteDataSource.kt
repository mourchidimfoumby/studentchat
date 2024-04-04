package com.example.data.remote

import com.example.data.remote.api.FriendsApi
import com.example.data.remote.model.FriendsRemote

internal class FriendsRemoteDataSource(
    private val friendsApi: FriendsApi
) {
    fun getAllFriends() = friendsApi.getAllFriends()
    fun getAllNotFriends() = friendsApi.getAllNotFriends()
    suspend fun getFriends(uid: String) = friendsApi.getFriends(uid)
    suspend fun addFriends(friendsRemote: FriendsRemote) = friendsApi.insertFriends(friendsRemote)
    suspend fun deleteFriends(friendsRemote: FriendsRemote) =
        friendsApi.deleteFriends(friendsRemote)
}