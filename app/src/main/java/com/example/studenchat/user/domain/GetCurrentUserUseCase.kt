package com.example.studenchat.user.domain

import com.example.studenchat.user.data.User
import com.example.studenchat.user.data.UserRepository

class GetCurrentUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): User? = userRepository.getCurrentUser()
}