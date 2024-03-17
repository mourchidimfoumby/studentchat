package com.example.studentchat.chat.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentchat.RemoveListenerUseCase
import com.example.studentchat.chat.data.Message
import com.example.studentchat.chat.data.MessageRepositoryImpl
import com.example.studentchat.chat.domain.GetAllMessageUseCase
import com.example.studentchat.chat.domain.GetLastMessageUseCase
import com.example.studentchat.chat.domain.SendMessageUseCase
import com.example.studentchat.conversation.data.Conversation
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import java.time.Instant

class ChatViewModel : ViewModel() {
    private val _lastMessage = MutableLiveData<Message>()
    val lastMessage: LiveData<Message> = _lastMessage
    private val _allMessages = MutableLiveData<List<Message>>()
    val allMessages: LiveData<List<Message>> = _allMessages
    private val getAllMessageUseCase: GetAllMessageUseCase by inject(GetAllMessageUseCase::class.java)
    private val getLastMessageUseCase: GetLastMessageUseCase by inject(GetLastMessageUseCase::class.java)
    private val sendMessageUseCase: SendMessageUseCase by inject(SendMessageUseCase::class.java)
    private val removeListenerUseCase: RemoveListenerUseCase by inject(RemoveListenerUseCase::class.java)
    private lateinit var conversation: Conversation

    operator fun invoke(conversation: Conversation) {
        this.conversation = conversation
        viewModelScope.launch {
            getAllMessageUseCase(conversation) { messageList ->
                messageList?.let {
                    _allMessages.value = it
                }
            }
        }
//        viewModelScope.launch {
//            getLastMessageUseCase(conversation) { message ->
//                message?.let {
//                    _lastMessage.value = it
//                }
//            }
//        }
    }

    override fun onCleared() {
        super.onCleared()
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