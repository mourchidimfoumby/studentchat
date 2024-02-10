package com.example.studenchat.domain.conversation

import com.example.studenchat.data.repositories.firebase.ConversationRepository
import com.example.studenchat.data.sources.Conversation

class GetAllConversationUseCase(
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(): List<Conversation> =
        conversationRepository.getAllConversations()
}