package com.example.studenchat.conversation.domain

import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.conversation.data.ConversationRepositoryImpl
import com.example.studenchat.conversation.data.UserConversationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateConversationUseCase (
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepositoryImpl: ConversationRepositoryImpl
) {
    suspend operator fun invoke(conversation: Conversation) {
        withContext(Dispatchers.IO) {
            userConversationRepository.createConversation(conversation)
            conversationRepositoryImpl.createConversation(conversation)
        }
    }
}