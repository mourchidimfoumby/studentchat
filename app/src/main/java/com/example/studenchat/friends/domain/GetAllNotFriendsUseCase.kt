package com.example.studenchat.friends.domain

import com.example.studenchat.friends.data.FriendsRepository
import com.example.studenchat.user.data.User
import com.example.studenchat.user.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllNotFriendsUseCase(
    private val userRepository: UserRepository,
    private val friendsRepository: FriendsRepository
) {
    suspend operator fun invoke(callback: (List<User>?) -> Unit) {
        friendsRepository.getAllFriendsUid().collect { friendsUids ->
            if (friendsUids != null) {
                withContext(Dispatchers.IO) {
                    val allUser = userRepository.getAllUser()
                    val allNotFriends = allUser?.filterNot { friendsUids.contains(it.uid) }
                    withContext(Dispatchers.Main) { callback(allNotFriends) }
                }
            }
        }
    }
}