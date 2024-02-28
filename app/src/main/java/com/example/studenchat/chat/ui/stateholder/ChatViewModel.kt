package com.example.studenchat.chat.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studenchat.chat.data.Message
import com.example.studenchat.chat.domain.GetAllMessageUseCase
import org.koin.java.KoinJavaComponent.inject

class ChatViewModel: ViewModel() {
    private val _message = MutableLiveData<Message>()
    val message: LiveData<Message> = _message
    private val getAllMessageUseCase: GetAllMessageUseCase by inject(GetAllMessageUseCase::class.java)

    init {
        initializeChat()
    }

    private fun initializeChat(){

    }
}