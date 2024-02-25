package com.example.studenchat.domain.usecase

import android.util.Log
import com.example.studenchat.data.repository.UserConversationRepository
import com.example.studenchat.domain.ConversationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.studenchat.data.source.Conversation

class GetAllConversationUseCase(
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepository: ConversationRepository,
    private val convertConversationDTOUseCase: ConvertConversationDTOUseCase
) {
    suspend operator fun invoke(callback: (List<Conversation>) -> Unit) =
        withContext(Dispatchers.IO){
            try {
                userConversationRepository.getAllIdsConversations().collect {
                    val conversationDTOList = conversationRepository.getConversation(it)
                    val conversationList = convertConversationDTOUseCase(conversationDTOList)
                    withContext(Dispatchers.Main){ callback(conversationList)}
                }
            }
            catch (e: Exception){
                Log.e(javaClass.name,"" ,e)
            }
    }
}