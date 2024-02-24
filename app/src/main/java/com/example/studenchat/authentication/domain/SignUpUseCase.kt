package com.example.studenchat.authentication.domain

import com.example.studenchat.authentication.AuthenticationRepository
import com.example.studenchat.data.source.User
<<<<<<< Updated upstream
import com.example.studenchat.domain.UserRepositoryImpl
=======
import com.example.studenchat.data.repository.UserRepository
import org.koin.java.KoinJavaComponent.inject
>>>>>>> Stashed changes
import java.lang.IllegalArgumentException

class SignUpUseCase {
    private val authenticationRepository = AuthenticationRepository()
<<<<<<< Updated upstream
    private val userRepositoryImpl = UserRepositoryImpl()
=======
    private val userRepository: UserRepository by inject(UserRepository::class.java)

>>>>>>> Stashed changes
    suspend operator fun invoke(user: User) {
        if(user.mail.isBlank() || user.password.isBlank()) throw IllegalArgumentException("Fields is blank")
        else{
            authenticationRepository.signUpWithEmailPassword(user.mail, user.password)
            authenticationRepository.logInWithEmailPassword(user.mail, user.password)
<<<<<<< Updated upstream
            userRepositoryImpl.createUser(user)
=======
            userRepository.createUser(user)
>>>>>>> Stashed changes
        }
    }

}
