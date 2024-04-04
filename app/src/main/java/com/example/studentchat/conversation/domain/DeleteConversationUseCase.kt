package com.example.studentchat.conversation.domain

import com.example.data.model.Conversation
import com.example.data.repository.ConversationRepository

class DeleteConversationUseCase(
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(conversation: Conversation) {
        conversationRepository.deleteConversation(conversation)
    }
}