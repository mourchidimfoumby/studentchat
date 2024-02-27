package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.FriendsRepository
import com.example.studenchat.data.source.User
import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.data.source.Conversation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.joinAll
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