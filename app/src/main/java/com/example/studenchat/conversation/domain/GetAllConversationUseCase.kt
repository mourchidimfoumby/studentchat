package com.example.studenchat.conversation.domain

import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.conversation.data.ConversationRepositoryImpl
import com.example.studenchat.conversation.data.UserConversationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllConversationUseCase(
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepositoryImpl: ConversationRepositoryImpl,
    private val convertConversationDTOUseCase: ConvertConversationDTOUseCase
) {
    suspend operator fun invoke(callback: (List<Conversation>?) -> Unit) =
        userConversationRepository.getAllIdsConversations().collect { conversationIds ->
            if (conversationIds == null) return@collect
            withContext(Dispatchers.IO) {
                val conversationDTOList =
                    conversationRepositoryImpl.getConversation(conversationIds)
                val conversationList = conversationDTOList?.let {
                    convertConversationDTOUseCase(it)
                }
                withContext(Dispatchers.Main) { callback(conversationList) }
            }
        }
}