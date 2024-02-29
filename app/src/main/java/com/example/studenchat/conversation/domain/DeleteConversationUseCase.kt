package com.example.studenchat.conversation.domain

import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.conversation.data.UserConversationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteConversationUseCase(
    private val userConversationRepository: UserConversationRepository
) {
    suspend operator fun invoke(conversation: Conversation) {
        withContext(Dispatchers.IO) {
            userConversationRepository.deleteConversation(conversation)
        }
    }
}