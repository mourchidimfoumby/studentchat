package com.example.studentchat.authentication.domain

import com.example.studentchat.authentication.AuthenticationManager

class LogInWithEmailPasswordUseCase(
    private val authenticationManager: AuthenticationManager
) {
    operator fun invoke(mail: String, password: String) {
        if(mail.isBlank() || password.isBlank()) throw IllegalArgumentException("Fields is blank")
        else authenticationManager.logInWithEmailPassword(mail, password)
    }

}
