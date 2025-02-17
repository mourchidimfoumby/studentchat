package com.example.ui.friends.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Friends
import com.example.data.model.User
import com.example.domain.friends.GetAllFriendsUseCase
import com.example.domain.friends.GetAllNotFriendsUseCase
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class FriendsViewModel : ViewModel() {
    private val _friends = MutableLiveData<List<Friends>>()
    val friends: LiveData<List<Friends>> = _friends
    private val _notFriends = MutableLiveData<List<User>>()
    val notFriends: LiveData<List<User>> = _notFriends
    private val getAllFriendsUseCase: GetAllFriendsUseCase by inject(GetAllFriendsUseCase::class.java)
    private val getAllNotFriendsUseCase: GetAllNotFriendsUseCase by inject(GetAllNotFriendsUseCase::class.java)
    init {
        initializeFriendsListener()
        initializeNotFriends()
    }

    private fun initializeFriendsListener(){
        viewModelScope.launch {
            getAllFriendsUseCase().collect {
                _friends.value = it

            }
        }
    }

     private fun initializeNotFriends(){
         viewModelScope.launch {
             getAllNotFriendsUseCase().collect {
                 _notFriends.value = it
             }
         }
     }
}