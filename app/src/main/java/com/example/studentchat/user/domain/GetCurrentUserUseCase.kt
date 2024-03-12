package com.example.studentchat.user.domain

import com.example.studentchat.user.data.User
import com.example.studentchat.user.data.UserRepository

class GetCurrentUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): User? = userRepository.getCurrentUser()
}