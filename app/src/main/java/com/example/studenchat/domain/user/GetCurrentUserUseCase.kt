package com.example.studenchat.domain.user

import com.example.studenchat.data.repositories.firebase.UserRepository

class GetCurrentUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getCurrentUser()
}