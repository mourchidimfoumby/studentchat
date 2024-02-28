package com.example.studenchat.user.domain

import com.example.studenchat.user.data.UserRepository

class GetUserWithUidUseCase (
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(uid: String) = userRepository.getUser(uid)
}