package com.example.studenchat.authentication.domain

import android.util.Log
import com.example.studenchat.authentication.AuthenticationRepository
import com.example.studenchat.data.User
import com.example.studenchat.repository.FirebaseParameters
import com.example.studenchat.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import java.lang.IllegalArgumentException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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
