package com.example.studenchat.authentication.domain

import android.util.Log
import com.example.studenchat.authentication.AuthenticationRepository
import com.example.studenchat.data.User
import com.example.studenchat.data.UserRepository
import java.lang.IllegalArgumentException

class SignUpUseCase {
    private val authenticationRepository = AuthenticationRepository()
    private val userRepository = UserRepository()
    suspend operator fun invoke(user: User) {
        if(user.mail.isBlank() || user.password.isBlank()) throw IllegalArgumentException("Fields is blank")
        else{
            authenticationRepository.signUpWithEmailPassword(user.mail, user.password)
            authenticationRepository.logInWithEmailPassword(user.mail, user.password)
            userRepository.createUser(user)
        }
    }

}
