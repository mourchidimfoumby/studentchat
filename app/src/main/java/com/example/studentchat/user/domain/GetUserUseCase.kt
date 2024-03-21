package com.example.studentchat.user.domain

import com.example.studentchat.user.data.User
import com.example.studentchat.user.data.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: String): User? = userRepository.getUser(uid)
}