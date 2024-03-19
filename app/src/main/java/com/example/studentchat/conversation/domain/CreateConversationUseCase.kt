package com.example.studentchat.conversation.domain

import com.example.studentchat.conversation.data.Conversation
import com.example.studentchat.conversation.data.ConversationRepository

class CreateConversationUseCase (
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(conversation: Conversation) {
        conversationRepository.createConversation(conversation)
    }
}