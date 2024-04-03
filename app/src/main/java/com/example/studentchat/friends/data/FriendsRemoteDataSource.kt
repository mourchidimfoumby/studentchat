package com.example.studentchat.friends.data

import com.example.data.model.User

class FriendsRemoteDataSource(
    private val friendsApi: FriendsApi
) {
    fun getAllFriends() = friendsApi.getAllFriends()
    fun getAllNotFriends() = friendsApi.getAllNotFriends()
    suspend fun getFriends(uid: String) = friendsApi.getFriends(uid)
    suspend fun addFriends(user: User) = friendsApi.insertFriends(user)
    suspend fun deleteFriends(friends: Friends) = friendsApi.deleteFriends(friends)
}