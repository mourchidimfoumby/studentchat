package com.example.domain.user

import com.example.data.remote.model.UserRemote
import com.example.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<UserRemote> = userRepository.currentUser
}