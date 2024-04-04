package com.example.studentchat.chat.ui.stateholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Conversation
import com.example.studentchat.RemoveListenerUseCase
import com.example.studentchat.chat.data.Message
import com.example.studentchat.chat.data.MessageApiImpl
import com.example.studentchat.chat.domain.GetAllMessageUseCase
import com.example.studentchat.chat.domain.SendMessageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import java.time.Instant

class ChatViewModel : ViewModel() {
    private val _message = MutableStateFlow<Message?>(null)
    val message: StateFlow<Message?> = _message
    private val getAllMessageUseCase: GetAllMessageUseCase by inject(GetAllMessageUseCase::class.java)
    private val sendMessageUseCase: SendMessageUseCase by inject(SendMessageUseCase::class.java)
    private val removeListenerUseCase: RemoveListenerUseCase by inject(RemoveListenerUseCase::class.java)
    private lateinit var conversation: Conversation

    operator fun invoke(conversation: Conversation) {
        this.conversation = conversation
        viewModelScope.launch {
            getAllMessageUseCase(conversation).collect {
                _message.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        removeListenerUseCase(MessageApiImpl())
    }

    fun sendMessage(messageText: String) {
        viewModelScope.launch {
            val timestamp = Instant.now()
            val message = Message(
                conversation.currentUser().toString(),
                messageText,
                timestamp.epochSecond
            )
            sendMessageUseCase(conversation, message)
        }
    }
}