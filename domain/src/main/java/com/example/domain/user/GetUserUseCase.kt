package com.example.domain.user

import com.example.data.remote.model.UserRemote
import com.example.data.repository.UserRepository


class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(uid: String): UserRemote? =
        userRepository.getUser(uid)
}