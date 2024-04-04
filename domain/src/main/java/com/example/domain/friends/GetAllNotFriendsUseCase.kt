package com.example.domain.friends

import com.example.data.model.User
import com.example.data.repository.FriendsRepository
import com.example.data.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {
    operator fun invoke(): Flow<List<User>> =
        friendsRepository.notFriends.map { notFriendsList ->
            notFriendsList.map { it.toUser() }
        }
}