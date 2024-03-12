package com.example.studentchat.friends.domain

import com.example.studentchat.friends.data.FriendsRepository
import com.example.studentchat.user.data.User
import com.example.studentchat.user.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GetAllFriendsUseCase(
    private val userRepository: UserRepository,
    private val friendsRepository: FriendsRepository
) {
    suspend operator fun invoke(callback: (List<User>?) -> Unit) {
        friendsRepository.getAllFriendsUid().collect { friendsUids ->
            if (friendsUids != null) {
                withContext(Dispatchers.IO) {
                    val users = async { userRepository.getUserList(friendsUids) }
                    val friends = users.await()?.filter { friendsUids.contains(it.uid) }
                    withContext(Dispatchers.Main) { callback(friends) }
                }
            }
        }
    }
}