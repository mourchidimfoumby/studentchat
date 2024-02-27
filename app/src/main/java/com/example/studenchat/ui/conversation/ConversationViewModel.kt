package com.example.studenchat.ui.conversation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.domain.ConversationRepository
import com.example.studenchat.domain.UserConversationRepositoryImpl
import com.example.studenchat.domain.usecase.CreateConversationUseCase
import com.example.studenchat.domain.usecase.DeleteConversationUseCase
import com.example.studenchat.domain.usecase.GetAllConversationUseCase
import com.example.studenchat.domain.usecase.RemoveListenerUseCase
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init
import org.koin.java.KoinJavaComponent.inject

class ConversationViewModel: ViewModel() {
    private val _conversations = MutableLiveData<List<Conversation>>()
    val conversations: LiveData<List<Conversation>> = _conversations
    private val createConversationUseCase : CreateConversationUseCase by inject(
        CreateConversationUseCase::class.java)
    private val deleteConversationUseCase : DeleteConversationUseCase by inject(
        DeleteConversationUseCase::class.java)
    private val getAllConversationUseCase: GetAllConversationUseCase by inject(
        GetAllConversationUseCase::class.java)
    private val removeListenerUseCase = RemoveListenerUseCase()
    init {
        initializeConversationListener()
    }

    override fun onCleared() {
        super.onCleared()
        removeListenerUseCase(ConversationRepository())
        removeListenerUseCase(UserConversationRepositoryImpl())
    }
    private fun initializeConversationListener(){
        viewModelScope.launch {
             getAllConversationUseCase {
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