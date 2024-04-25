package com.example.domain.friends

import com.example.data.model.User
import com.example.data.repository.FriendsRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {
    operator fun invoke(): Flow<List<User>> = friendsRepository.getAllNotFriends()
}