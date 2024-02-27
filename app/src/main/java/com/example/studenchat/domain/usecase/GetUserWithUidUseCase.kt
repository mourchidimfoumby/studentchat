package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.UserRepository

class GetUserWithUidUseCase (
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(uid: String) = userRepository.getUser(uid)
}