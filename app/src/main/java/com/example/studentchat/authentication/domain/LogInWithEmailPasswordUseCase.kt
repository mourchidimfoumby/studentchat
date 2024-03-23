package com.example.studentchat.authentication.domain

import com.example.studentchat.AuthenticationState
import com.example.studentchat.authentication.AuthenticationManager

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
