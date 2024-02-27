package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.FriendsRepository
import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.data.source.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetAllNotFriendsUseCase(
    private val userRepository: UserRepository,
    private val friendsRepository: FriendsRepository
) {
    suspend operator fun invoke(callback: (List<User>?) -> Unit) =
        friendsRepository.getAllFriendsUid().collect { friendsUids ->
            if(friendsUids == null) return@collect
            CoroutineScope(Dispatchers.IO).launch {
                val allUser = userRepository.getAllUser()
                val allNotFriends = allUser?.filterNot { friendsUids.contains(it.uid) }
                withContext(Dispatchers.Main) { callback(allNotFriends) }
            }.join()
    }
}