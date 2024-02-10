package com.example.studenchat.domain.user

import com.example.studenchat.data.repositories.firebase.UserRepository
import com.example.studenchat.data.sources.User

class GetAllUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<User> = userRepository.getAllUser()
}