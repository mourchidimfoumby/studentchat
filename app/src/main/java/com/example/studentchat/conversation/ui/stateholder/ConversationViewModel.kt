package com.example.studentchat.conversation.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Conversation
import com.example.data.remote.api.ConversationApiImpl
import com.example.studentchat.RemoveListenerUseCase
import com.example.domain.conversation.CreateConversationUseCase
import com.example.domain.conversation.DeleteConversationUseCase
import com.example.domain.conversation.GetAllConversationsUseCase
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
    private val getAllConversationsUseCase: GetAllConversationsUseCase by inject(
        GetAllConversationsUseCase::class.java
    )

    init {
        initializeConversationListener()
    }

    override fun onCleared() {
        super.onCleared()
        removeListenerUseCase(ConversationApiImpl())
    }

    private fun initializeConversationListener(){
        viewModelScope.launch {
            getAllConversationsUseCase().collect {
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