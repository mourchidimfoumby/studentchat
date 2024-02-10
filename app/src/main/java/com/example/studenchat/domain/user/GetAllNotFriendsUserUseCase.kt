package com.example.studenchat.domain.user

import com.example.studenchat.data.repositories.firebase.UserRepository

class GetAllNotFriendsUserUseCase(private val userRepository: UserRepository){
    /*suspend operator fun invoke(): List<User>{
        return userRepository.getAllNotFriendsUser()
    }
*/
}