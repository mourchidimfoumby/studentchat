package com.example.authentication.domain

import com.example.authentication.AuthenticationManager
import com.example.authentication.AuthenticationState

class LogInWithEmailPasswordUseCase(
    private val authenticationManager: AuthenticationManager
) {
    operator fun invoke(
        mail: String,
        password: String,
        callback: (Result<AuthenticationState>) -> Unit
    ) {
        if (mail.isBlank() || password.isBlank()) callback(Result.failure(Exception("Empty fields")))
        else authenticationManager.logInWithEmailPassword(mail, password, callback)
    }

}
