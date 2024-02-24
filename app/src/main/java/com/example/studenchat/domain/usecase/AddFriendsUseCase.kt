package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.FriendsRepository
import com.example.studenchat.data.source.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFriendsUseCase(private val friendsRepository: FriendsRepository) {
    operator fun invoke(user: User){
        CoroutineScope(Dispatchers.IO).launch {
            friendsRepository.addFriend(user)
        }
    }
}