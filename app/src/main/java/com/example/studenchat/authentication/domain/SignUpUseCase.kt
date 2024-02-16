package com.example.studenchat.authentication.domain

import com.example.studenchat.authentication.AuthenticationRepository
import com.example.studenchat.data.source.User
import com.example.studenchat.domain.UserRepositoryImpl
import java.lang.IllegalArgumentException

class SignUpUseCase {
    private val authenticationRepository = AuthenticationRepository()
    private val userRepositoryImpl = UserRepositoryImpl()
    suspend operator fun invoke(user: User) {
        if(user.mail.isBlank() || user.password.isBlank()) throw IllegalArgumentException("Fields is blank")
        else{
            authenticationRepository.signUpWithEmailPassword(user.mail, user.password)
            authenticationRepository.logInWithEmailPassword(user.mail, user.password)
            userRepositoryImpl.createUser(user)
        }
    }

}
