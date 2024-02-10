package com.example.studenchat.ui.stateholders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studenchat.data.repositories.firebase.ConversationRepository
import com.example.studenchat.data.repositories.firebase.UserRepository
import com.example.studenchat.data.sources.Conversation
import com.example.studenchat.domain.conversation.CreateConversationUseCase
import com.example.studenchat.domain.conversation.DeleteConversationUseCase
import com.example.studenchat.domain.conversation.GetAllConversationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConversationViewModel: ViewModel() {
    private val _conversations = MutableLiveData<List<Conversation>>()
    val conversation: LiveData<List<Conversation>> = _conversations
    private val createConversationUseCase =
        CreateConversationUseCase(ConversationRepository(), UserRepository())
    private val deleteConversationUseCase =
        DeleteConversationUseCase(ConversationRepository())
    init {
        initializeConversation()
    }
    private fun initializeConversation(){
        CoroutineScope(Dispatchers.IO).launch {
            val allConversation = async { GetAllConversationUseCase(ConversationRepository()).invoke() }
            withContext(Dispatchers.Main) {
                _conversations.value = allConversation.await()
            }
        }
    }
    fun createConversation(conversation: Conversation){
        CoroutineScope(Dispatchers.IO).launch {
            createConversationUseCase(conversation)
        }
    }
    fun deleteConversation(conversation: Conversation){
        CoroutineScope(Dispatchers.IO).launch {
            deleteConversationUseCase(conversation)
        }
    }
}