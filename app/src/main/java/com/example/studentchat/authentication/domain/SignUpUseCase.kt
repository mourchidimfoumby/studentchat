package com.example.studentchat.authentication.domain

import com.example.studentchat.authentication.AuthenticationRepository
import com.example.studentchat.user.data.UserRepository
import com.example.studentchat.user.data.User
import org.koin.java.KoinJavaComponent.inject

class SignUpUseCase {
    private val authenticationRepository = AuthenticationRepository()
    private val userRepository: UserRepository by inject(UserRepository::class.java)

    suspend operator fun invoke(user: User) {
        if(user.mail.isBlank() || user.password.isBlank()) throw IllegalArgumentException("Fields is blank")
        else{
            authenticationRepository.signUpWithEmailPassword(user.mail, user.password)
            authenticationRepository.logInWithEmailPassword(user.mail, user.password)
            userRepository.createUser(user)
        }
    }

}
