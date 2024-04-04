package com.example.domain.conversation

import com.example.data.model.Conversation
import com.example.data.repository.ConversationRepository
import com.example.data.toRemote

class CreateConversationUseCase (
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(conversation: Conversation) {
        conversationRepository.createConversation(conversation.toRemote())
    }
}