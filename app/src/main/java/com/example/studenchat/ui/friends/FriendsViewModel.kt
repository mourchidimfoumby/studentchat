package com.example.studenchat.ui.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenchat.data.source.User
import com.example.studenchat.domain.FriendsRepositoryImpl
import com.example.studenchat.domain.usecase.GetAllFriendsUseCase
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class FriendsViewModel : ViewModel() {
    private val _friends = MutableLiveData<List<User>>()
    val friends: LiveData<List<User>> = _friends
    private val _notFriends = MutableLiveData<List<User>>()
    val notFriends: LiveData<List<User>> = _notFriends
    private val getAllFriendsUseCase: GetAllFriendsUseCase by inject(GetAllFriendsUseCase::class.java)
    init {
        initializeFriends()
        /*initializeNotFriends()*/
    }
    private fun initializeFriends(){
        viewModelScope.launch {
            _friends.value = getAllFriendsUseCase()
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