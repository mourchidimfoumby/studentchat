package com.example.studenchat.ui.stateholders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studenchat.data.sources.User
import com.example.studenchat.data.repositories.firebase.UserRepository
import com.example.studenchat.domain.user.GetCurrentUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel(){
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val currentUser = async { GetCurrentUserUseCase(UserRepository()).invoke()}
            withContext(Dispatchers.Main) {
                _user.value = currentUser.await()
            }
        }
    }
}