package com.example.studentchat.friends.domain

import com.example.studentchat.friends.data.Friends
import com.example.studentchat.friends.data.FriendsRepository
import kotlinx.coroutines.flow.Flow

class GetAllFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {
    operator fun invoke(): Flow<List<Friends>> = friendsRepository.friends
}