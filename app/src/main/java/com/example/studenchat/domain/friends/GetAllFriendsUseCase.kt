package com.example.studenchat.domain.friends

import com.example.studenchat.data.repositories.firebase.FriendsRepository
import com.example.studenchat.data.sources.User

class GetAllFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {
    suspend operator fun invoke(): List<User> = friendsRepository.getAllFriends()
}