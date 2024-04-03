package com.example.studentchat.authentication.domain

import com.example.studentchat.AuthenticationState
import com.example.studentchat.authentication.AuthenticationManager
import com.example.data.remote.model.UserRemote
import com.example.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SignUpWithEmailPasswordUseCase(
    private val authenticationRepository: AuthenticationManager,
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userRemote: UserRemote): Flow<Result<AuthenticationState>> =
        callbackFlow {
            if (userRemote.mail.isBlank() || userRemote.password.isBlank()) trySend(
                Result.failure(
                    Exception("Mail or password is empty")
                )
            )
        else{
                val callback: (Result<AuthenticationState>) -> Unit = {
                    trySend(it)
                }
            authenticationRepository.signUpWithEmailPassword(
                userRemote.mail,
                userRemote.password,
                callback
            )
            authenticationRepository.logInWithEmailPassword(
                userRemote.mail,
                userRemote.password,
                callback
            )
            userRepository.createUser(userRemote)
        }
    }
}
