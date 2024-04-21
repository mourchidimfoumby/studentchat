package com.example.domain.friends

import com.example.data.model.User
import com.example.data.repository.FriendsRepository

class AddFriendsUseCase(private val friendsRepository: FriendsRepository) {
    suspend operator fun invoke(user: User) {
        TODO()
//        friendsRepository.addFriends(user.toFriends().toRemote())
    }
}