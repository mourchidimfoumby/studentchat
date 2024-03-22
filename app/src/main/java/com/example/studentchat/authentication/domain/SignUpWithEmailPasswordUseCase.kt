package com.example.studentchat.authentication.domain

import com.example.studentchat.authentication.AuthenticationManager
import com.example.studentchat.user.data.UserApiModel
import com.example.studentchat.user.data.UserRepository

class SignUpWithEmailPasswordUseCase(
    private val authenticationRepository: AuthenticationManager,
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userApiModel: UserApiModel) {
        if (userApiModel.mail.isBlank() || userApiModel.password.isBlank()) throw IllegalArgumentException(
            "Fields is blank"
        )
        else{
            authenticationRepository.signUpWithEmailPassword(
                userApiModel.mail,
                userApiModel.password
            )
            authenticationRepository.logInWithEmailPassword(
                userApiModel.mail,
                userApiModel.password
            )
            userRepository.createUser(userApiModel)
        }
    }
}
