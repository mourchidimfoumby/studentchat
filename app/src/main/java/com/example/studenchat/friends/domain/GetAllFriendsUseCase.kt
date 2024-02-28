package com.example.studenchat.friends.domain

import com.example.studenchat.friends.data.FriendsRepository
import com.example.studenchat.user.data.User
import com.example.studenchat.user.data.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetAllFriendsUseCase(
    private val userRepository: UserRepository,
    private val friendsRepository: FriendsRepository
) {
    suspend operator fun invoke(callback: (List<User>?) -> Unit) =
        friendsRepository.getAllFriendsUid().collect { friendsUids ->
            if(friendsUids == null) return@collect
            CoroutineScope(Dispatchers.IO).launch {
                val users = async { userRepository.getUserList(friendsUids) }
                val friends = users.await()?.filter { friendsUids.contains(it.uid) }
                withContext(Dispatchers.Main){ callback(friends) }
            }.join()
    }
}