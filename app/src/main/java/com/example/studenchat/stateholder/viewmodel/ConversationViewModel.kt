package com.example.studenchat.stateholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenchat.data.interfaces.ConversationRepository
import com.example.studenchat.domain.UserRepositoryImpl
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.domain.usecase.CreateConversationUseCase
import com.example.studenchat.domain.usecase.DeleteConversationUseCase
import com.example.studenchat.domain.usecase.GetAllConversationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinApplication.Companion.init
import org.koin.java.KoinJavaComponent.inject

class ConversationViewModel: ViewModel() {
    private val _conversations = MutableLiveData<List<Conversation>>()
    val conversations: LiveData<List<Conversation>> = _conversations
    private val createConversationUseCase : CreateConversationUseCase by inject(CreateConversationUseCase::class.java)
    private val deleteConversationUseCase : DeleteConversationUseCase by inject(DeleteConversationUseCase::class.java)
    private val getAllConversationUseCase : GetAllConversationUseCase by inject(GetAllConversationUseCase::class.java)
    init {
        initializeConversationListener()
    }
    private fun initializeConversationListener(){
        viewModelScope.launch {
            _conversations.value = getAllConversationUseCase()
        }
    }
    fun createConversation(conversation: Conversation) = createConversationUseCase(conversation)
    fun deleteConversation(conversation: Conversation) = deleteConversationUseCase(conversation)
}