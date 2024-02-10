package com.example.studenchat.domain.conversation

import com.example.studenchat.data.repositories.firebase.ConversationRepository
import com.example.studenchat.data.sources.Conversation

class DeleteConversationUseCase(
    private val conversationRepository: ConversationRepository,
) {
    suspend operator fun invoke(conversation: Conversation){
        conversationRepository.deleteConversation(conversation)
    }
}