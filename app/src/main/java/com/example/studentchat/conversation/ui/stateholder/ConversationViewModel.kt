package com.example.studentchat.conversation.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentchat.RemoveListenerUseCase
import com.example.studentchat.conversation.data.Conversation
import com.example.studentchat.conversation.data.ConversationApiImpl
import com.example.studentchat.conversation.data.ConversationRepository
import com.example.studentchat.conversation.domain.CreateConversationUseCase
import com.example.studentchat.conversation.domain.DeleteConversationUseCase
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ConversationViewModel: ViewModel() {
    private val _conversations = MutableLiveData<List<Conversation>>()
    val conversations: LiveData<List<Conversation>> = _conversations
    private val createConversationUseCase : CreateConversationUseCase by inject(
        CreateConversationUseCase::class.java)
    private val deleteConversationUseCase : DeleteConversationUseCase by inject(
        DeleteConversationUseCase::class.java)
    private val removeListenerUseCase: RemoveListenerUseCase by inject(
        RemoveListenerUseCase::class.java)
    private val conversationRepository: ConversationRepository by inject(
        ConversationRepository::class.java)

    init {
        initializeConversationListener()
    }

    override fun onCleared() {
        super.onCleared()
        removeListenerUseCase(ConversationApiImpl())
    }

    private fun initializeConversationListener(){
        viewModelScope.launch {
            conversationRepository.conversations.collect {
                _conversations.value = it
            }
        }
    }

    fun createConversation(conversation: Conversation) {
        viewModelScope.launch {
            createConversationUseCase(conversation)
        }
    }

    fun deleteConversation(conversation: Conversation) {
        viewModelScope.launch {
            deleteConversationUseCase(conversation)
        }
    }
}