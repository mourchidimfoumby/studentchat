package com.example.studenchat.ui.stateholders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenchat.data.repositories.firebase.FriendsRepository
import com.example.studenchat.data.sources.User
import com.example.studenchat.domain.friends.GetAllFriendsUseCase
import kotlinx.coroutines.launch

class FriendsViewModel : ViewModel() {
    private val _friends = MutableLiveData<List<User>>()
    val friends: LiveData<List<User>> = _friends
    private val _notFriends = MutableLiveData<List<User>>()
    val notFriends: LiveData<List<User>> = _notFriends

    init {
        initializeFriends()
        /*initializeNotFriends()*/
    }

    private fun initializeFriends(){
        viewModelScope.launch {
            val allFriends = GetAllFriendsUseCase(FriendsRepository()).invoke()
            _friends.value = allFriends
        }
    }
   /* private fun initializeNotFriends(){
        viewModelScope.launch {
            val allNotFriends = async { GetAllNotFriendsUserUseCase(UserRepository()).invoke() }
            withContext(Dispatchers.Main) {
                _notFriends.value = allNotFriends.await()
            }
        }
    }*/
}