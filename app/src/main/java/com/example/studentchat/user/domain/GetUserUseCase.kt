package com.example.studentchat.user.domain

import com.example.data.model.User
import com.example.data.repository.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: String): User? = userRepository.getUser(uid)
}