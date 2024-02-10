package com.example.studenchat.domain.user

import com.example.studenchat.data.repositories.firebase.UserRepository

class GetUserWithUID (
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(uid: String) = userRepository.getUser(uid)
}