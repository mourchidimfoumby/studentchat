package com.example.studentchat.authentication.domain

import com.example.studentchat.authentication.AuthenticationRepository
import com.example.studentchat.user.data.UserApiModel
import com.example.studentchat.user.data.UserRepository

class SignUpUseCase(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository
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
