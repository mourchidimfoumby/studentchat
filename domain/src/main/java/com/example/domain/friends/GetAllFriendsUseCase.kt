package com.example.domain.friends

import com.example.data.model.Friends
import com.example.data.repository.FriendsRepository
import com.example.data.toFriends
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {
    operator fun invoke(): Flow<List<Friends>> =
        friendsRepository.friends.map { friendsList ->
            friendsList.map { it.toFriends() }
        }
}