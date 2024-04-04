package com.example.studentchat.conversation.domain

import com.example.data.model.Conversation
import com.example.data.repository.ConversationRepository

class CreateConversationUseCase (
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(conversation: Conversation) {
        conversationRepository.createConversation(conversation)
    }
}