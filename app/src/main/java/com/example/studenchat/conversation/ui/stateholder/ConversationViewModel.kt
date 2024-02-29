package com.example.studenchat.conversation.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenchat.RemoveListenerUseCase
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.conversation.data.ConversationRepositoryImpl
import com.example.studenchat.conversation.data.UserConversationRepositoryImpl
import com.example.studenchat.conversation.domain.CreateConversationUseCase
import com.example.studenchat.conversation.domain.DeleteConversationUseCase
import com.example.studenchat.conversation.domain.GetAllConversationUseCase
import kotlinx.coroutines.launch
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
        removeListenerUseCase(ConversationRepositoryImpl())
        removeListenerUseCase(UserConversationRepositoryImpl())
    }
    private fun initializeConversationListener(){
        viewModelScope.launch {
            getAllConversationUseCase { conversationList ->
                conversationList?.let {
                    _conversations.value = it
                }
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