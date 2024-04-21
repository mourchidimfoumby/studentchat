package com.example.domain.friends

import com.example.data.model.Friends
import com.example.data.repository.FriendsRepository
import kotlinx.coroutines.flow.Flow

class GetAllFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {
    operator fun invoke(): Flow<List<Friends>> = TODO()
//        friendsRepository.friends.map { friendsList ->
//            friendsList.map { it.toFriends() }
//        }
}