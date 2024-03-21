package com.example.studentchat.friends.domain

import com.example.studentchat.friends.data.FriendsRepository
import com.example.studentchat.user.data.User
import kotlinx.coroutines.flow.Flow

class GetAllNotFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {
    operator fun invoke(): Flow<List<User>> = friendsRepository.notFriends
}