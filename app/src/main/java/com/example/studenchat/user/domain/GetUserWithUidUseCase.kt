package com.example.studenchat.user.domain

import com.example.studenchat.user.data.User
import com.example.studenchat.user.data.UserRepository

class GetUserWithUidUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: String): User? = userRepository.getUser(uid)
}