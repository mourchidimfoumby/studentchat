package com.example.studentchat.friends.domain

import com.example.data.model.Friends
import com.example.data.repository.FriendsRepository
import kotlinx.coroutines.flow.Flow

class GetAllFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {
    operator fun invoke(): Flow<List<Friends>> = friendsRepository.friends
}