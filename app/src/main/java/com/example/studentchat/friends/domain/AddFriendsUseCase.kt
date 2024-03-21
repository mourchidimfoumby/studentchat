package com.example.studentchat.friends.domain

import com.example.studentchat.friends.data.FriendsRepository
import com.example.studentchat.user.data.User

class AddFriendsUseCase(private val friendsRepository: FriendsRepository) {
    suspend operator fun invoke(user: User) {
        friendsRepository.addFriends(user)
    }
}