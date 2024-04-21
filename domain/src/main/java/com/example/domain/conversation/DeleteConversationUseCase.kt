package com.example.domain.conversation

import com.example.data.model.Conversation
import com.example.data.repository.ConversationRepository

class DeleteConversationUseCase(
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(conversation: Conversation) {
        conversationRepository.deleteConversation(conversation)
    }
}