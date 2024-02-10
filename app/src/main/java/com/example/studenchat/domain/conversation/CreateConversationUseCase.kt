package com.example.studenchat.domain.conversation

import com.example.studenchat.data.repositories.firebase.ConversationRepository
import com.example.studenchat.data.repositories.firebase.UserRepository
import com.example.studenchat.data.sources.Conversation

class CreateConversationUseCase(
    private val conversationRepository: ConversationRepository,
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(conversation: Conversation) {
        conversationRepository.createConversation(conversation)
    }
}