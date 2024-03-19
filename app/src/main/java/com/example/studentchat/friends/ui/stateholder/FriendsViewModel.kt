package com.example.studentchat.friends.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentchat.user.data.User
import com.example.studentchat.user.data.UserRepositoryImpl
import com.example.studentchat.friends.domain.GetAllFriendsUseCase
import com.example.studentchat.friends.domain.GetAllNotFriendsUseCase
import com.example.studentchat.RemoveListenerUseCase
import com.example.studentchat.friends.data.FriendsRepositoryImpl
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
}