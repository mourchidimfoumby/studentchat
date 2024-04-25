package com.example.authentication.domain

import com.example.authentication.AuthenticationManager
import com.example.authentication.AuthenticationState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SignUpWithEmailPasswordUseCase(
    private val authenticationManager: AuthenticationManager,
) {
    operator fun invoke(mail: String, password: String): Flow<Result<AuthenticationState>> =
        callbackFlow {
            val callback: (Result<AuthenticationState>) -> Unit = {
                trySend(it)
            }
            authenticationManager.signUpWithEmailPassword(
                mail,
                password,
                callback
            )
            awaitClose()
        }
}
