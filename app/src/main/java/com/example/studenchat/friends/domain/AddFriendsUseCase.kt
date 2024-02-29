package com.example.studenchat.friends.domain

import com.example.studenchat.friends.data.FriendsRepository
import com.example.studenchat.user.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddFriendsUseCase(private val friendsRepository: FriendsRepository) {
    suspend operator fun invoke(user: User) {
        withContext(Dispatchers.IO) {
            friendsRepository.addFriend(user)
        }
    }
}