package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.FriendsRepository
import com.example.studenchat.data.source.User
import com.example.studenchat.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class GetAllFriendsUseCase(
    private val userRepository: UserRepository,
    private val friendsRepository: FriendsRepository
) {
    suspend operator fun invoke(): List<User> {
        val users = userRepository.getAllUser()
        val friendsUids = friendsRepository.getAllFriendsUid()
        return users.filter { friendsUids.contains(it.uid) }
    }
}