package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.UserConversationRepository
import com.example.studenchat.data.source.Conversation

class DeleteConversationUseCase(
    private val userConversationRepository: UserConversationRepository,
) {
    suspend operator fun invoke(conversation: Conversation) =
        userConversationRepository.deleteConversation(conversation)
}