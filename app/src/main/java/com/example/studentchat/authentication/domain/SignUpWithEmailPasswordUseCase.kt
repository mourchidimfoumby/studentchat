package com.example.studentchat.authentication.domain

import com.example.studentchat.AuthenticationState
import com.example.studentchat.authentication.AuthenticationManager
import com.example.studentchat.user.data.UserApiModel
import com.example.studentchat.user.data.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SignUpWithEmailPasswordUseCase(
    private val authenticationRepository: AuthenticationManager,
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userApiModel: UserApiModel): Flow<Result<AuthenticationState>> =
        callbackFlow {
            if (userApiModel.mail.isBlank() || userApiModel.password.isBlank()) trySend(
                Result.failure(
                    Exception("Mail or password is empty")
                )
            )
        else{
                val callback: (Result<AuthenticationState>) -> Unit = {
                    trySend(it)
                }
            authenticationRepository.signUpWithEmailPassword(
                userApiModel.mail,
                userApiModel.password,
                callback
            )
            authenticationRepository.logInWithEmailPassword(
                userApiModel.mail,
                userApiModel.password,
                callback
            )
            userRepository.createUser(userApiModel)
        }
    }
}
