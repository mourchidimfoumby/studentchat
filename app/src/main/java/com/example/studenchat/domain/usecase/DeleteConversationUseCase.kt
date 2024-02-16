package com.example.studenchat.domain.usecase

import com.example.studenchat.data.interfaces.ConversationRepository
import com.example.studenchat.data.source.Conversation

class DeleteConversationUseCase(
    private val conversationRepository: ConversationRepository,
) {
    operator fun invoke(conversation: Conversation){
        conversationRepository.deleteConversation(conversation)
    }
}