package com.example.studenchat.ui.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.data.source.User
import com.example.studenchat.domain.FriendsRepositoryImpl
import com.example.studenchat.domain.UserRepositoryImpl
import com.example.studenchat.domain.usecase.GetAllFriendsUseCase
import com.example.studenchat.domain.usecase.GetAllNotFriendsUseCase
import com.example.studenchat.domain.usecase.RemoveListenerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class FriendsViewModel : ViewModel() {
    private val _friends = MutableLiveData<List<User>>()
    val friends: LiveData<List<User>> = _friends
    private val _notFriends = MutableLiveData<List<User>>()
    val notFriends: LiveData<List<User>> = _notFriends
    private val getAllFriendsUseCase: GetAllFriendsUseCase by inject(GetAllFriendsUseCase::class.java)
    private val getAllNotFriendsUseCase: GetAllNotFriendsUseCase by inject(GetAllNotFriendsUseCase::class.java)
    private val removeListenerUseCase: RemoveListenerUseCase by inject(RemoveListenerUseCase::class.java)
    init {
        initializeFriendsListener()
        initializeNotFriends()
    }
    private fun initializeFriendsListener(){
        viewModelScope.launch {
            getAllFriendsUseCase{ friendsList ->
                friendsList?.let {
                    _friends.value = it
                }
            }
        }
    }
     private fun initializeNotFriends(){
         viewModelScope.launch {
             getAllNotFriendsUseCase { notFriendsList ->
                 notFriendsList?.let {
                     _notFriends.value = it
                 }
             }
         }
     }
    override fun onCleared() {
        super.onCleared()
        removeListenerUseCase(UserRepositoryImpl())
        removeListenerUseCase(FriendsRepositoryImpl())
    }
}