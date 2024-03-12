package com.example.studentchat.friends.domain

import com.example.studentchat.friends.data.FriendsRepository
import com.example.studentchat.user.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddFriendsUseCase(private val friendsRepository: FriendsRepository) {
    suspend operator fun invoke(user: User) {
        withContext(Dispatchers.IO) {
            friendsRepository.addFriend(user)
        }
    }
}