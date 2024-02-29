package com.example.studenchat.chat.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenchat.RemoveListenerUseCase
import com.example.studenchat.chat.data.Message
import com.example.studenchat.chat.data.MessageRepositoryImpl
import com.example.studenchat.chat.domain.GetAllMessageUseCase
import com.example.studenchat.chat.domain.GetLastMessageUseCase
import com.example.studenchat.chat.domain.SendMessageUseCase
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.user.domain.GetCurrentUserUseCase
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import java.time.ZoneId
import java.time.ZonedDateTime

class ChatViewModel : ViewModel() {
    private val _lastMessage = MutableLiveData<Message>()
    val lastMessage: LiveData<Message> = _lastMessage
    private val _allMessages = MutableLiveData<List<Message>>()
    val allMessages: LiveData<List<Message>> = _allMessages
    private val getAllMessageUseCase: GetAllMessageUseCase by inject(GetAllMessageUseCase::class.java)
    private val getLastMessageUseCase: GetLastMessageUseCase by inject(GetLastMessageUseCase::class.java)
    private val sendMessageUseCase: SendMessageUseCase by inject(SendMessageUseCase::class.java)
    private val getCurrentUserUseCase: GetCurrentUserUseCase by inject(GetCurrentUserUseCase::class.java)
    private val removeListenerUseCase: RemoveListenerUseCase by inject(RemoveListenerUseCase::class.java)

    operator fun invoke(conversation: Conversation) {
        initializeChat(conversation)
    }

    private fun initializeChat(conversation: Conversation) {
        viewModelScope.launch {
            getAllMessageUseCase(conversation) { messageList ->
                messageList?.let {
                    _allMessages.value = it
                }
            }
//            getLastMessageUseCase(conversation){
//                it?.let {
//                   _lastMessage.value = it
//                }
//            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        removeListenerUseCase(MessageRepositoryImpl())
    }

    fun sendMessage(conversation: Conversation, messageText: String) {
        viewModelScope.launch {
            val currentUser = getCurrentUserUseCase()
            val zoneDateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"))
            val timestamp = zoneDateTime.toInstant().epochSecond
            currentUser?.let {
                val message = Message(
                    currentUser.uid,
                    messageText,
                    timestamp.toString()
                )
                sendMessageUseCase(conversation, message)
            } ?: throw Exception("Failed to get the current user")
        }
    }
}