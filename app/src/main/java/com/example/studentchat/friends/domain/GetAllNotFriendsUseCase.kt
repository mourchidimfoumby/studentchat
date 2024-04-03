package com.example.studentchat.friends.domain

import com.example.studentchat.friends.data.FriendsRepository
import com.example.data.model.User
import kotlinx.coroutines.flow.Flow

class GetAllNotFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {
    operator fun invoke(): Flow<List<User>> = friendsRepository.notFriends
}