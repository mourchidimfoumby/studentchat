package com.example.studenchat.domain.usecase

import android.util.Log
import com.example.studenchat.data.repository.UserConversationRepository
import com.example.studenchat.domain.ConversationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.studenchat.data.source.Conversation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GetAllConversationUseCase(
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepository: ConversationRepository,
    private val convertConversationDTOUseCase: ConvertConversationDTOUseCase
) {
    suspend operator fun invoke(callback: (List<Conversation>?) -> Unit) =
        userConversationRepository.getAllIdsConversations().collect { conversationIds ->
            if(conversationIds == null) return@collect
            CoroutineScope(Dispatchers.IO).launch {
                    val conversationDTOList = conversationRepository.getConversation(conversationIds)
                    val conversationList = conversationDTOList?.let{
                        convertConversationDTOUseCase(it)
                    }
                    withContext(Dispatchers.Main){ callback(conversationList)}
                }.join()
    }
}