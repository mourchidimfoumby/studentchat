package com.example.studentchat.user.domain

import com.example.studentchat.user.data.UserApiModel
import com.example.studentchat.user.data.UserRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<UserApiModel> = userRepository.currentUser
}