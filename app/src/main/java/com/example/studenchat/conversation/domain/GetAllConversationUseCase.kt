package com.example.studenchat.conversation.domain

import com.example.studenchat.conversation.data.UserConversationRepository
import com.example.studenchat.conversation.data.ConversationRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.studenchat.conversation.data.Conversation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GetAllConversationUseCase(
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepositoryImpl: ConversationRepositoryImpl,
    private val convertConversationDTOUseCase: ConvertConversationDTOUseCase
) {
    suspend operator fun invoke(callback: (List<Conversation>?) -> Unit) =
        userConversationRepository.getAllIdsConversations().collect { conversationIds ->
            if(conversationIds == null) return@collect
            CoroutineScope(Dispatchers.IO).launch {
                    val conversationDTOList = conversationRepositoryImpl.getConversation(conversationIds)
                    val conversationList = conversationDTOList?.let{
                        convertConversationDTOUseCase(it)
                    }
                    withContext(Dispatchers.Main){ callback(conversationList)}
                }.join()
    }
}