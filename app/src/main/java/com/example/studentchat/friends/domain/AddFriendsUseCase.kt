package com.example.studentchat.friends.domain

import com.example.data.repository.FriendsRepository
import com.example.data.model.User

class AddFriendsUseCase(private val friendsRepository: FriendsRepository) {
    suspend operator fun invoke(user: User) {
        friendsRepository.addFriends(user)
    }
}