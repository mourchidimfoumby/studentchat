package com.example.studenchat.friends.domain

import com.example.studenchat.friends.data.FriendsRepository
import com.example.studenchat.user.data.User
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