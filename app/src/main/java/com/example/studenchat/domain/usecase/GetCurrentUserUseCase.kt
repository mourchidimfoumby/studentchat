package com.example.studenchat.domain.usecase

import com.example.studenchat.data.source.User
import com.example.studenchat.data.repository.UserRepository

class GetCurrentUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getCurrentUser()
}