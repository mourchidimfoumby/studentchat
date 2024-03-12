package com.example.studentchat.authentication.domain

import com.example.studentchat.authentication.AuthenticationRepository
import java.lang.IllegalArgumentException

class LogInWithEmailPasswordUseCase {
    private val authenticationRepository = AuthenticationRepository()
    suspend operator fun invoke(mail: String, password: String){
        if(mail.isBlank() || password.isBlank()) throw IllegalArgumentException("Fields is blank")
        else{
            authenticationRepository.logInWithEmailPassword(mail, password)
        }
    }

}
