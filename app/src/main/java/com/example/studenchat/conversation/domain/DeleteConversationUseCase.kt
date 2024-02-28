package com.example.studenchat.conversation.domain

import com.example.studenchat.conversation.data.UserConversationRepository
import com.example.studenchat.conversation.data.Conversation

class DeleteConversationUseCase(
    private val userConversationRepository: UserConversationRepository,
) {
    suspend operator fun invoke(conversation: Conversation) =
        userConversationRepository.deleteConversation(conversation)
}