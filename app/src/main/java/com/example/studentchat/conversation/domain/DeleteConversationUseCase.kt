package com.example.studentchat.conversation.domain

import com.example.studentchat.conversation.data.Conversation
import com.example.studentchat.conversation.data.ConversationRepository

class DeleteConversationUseCase(
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(conversation: Conversation) {
        conversationRepository.deleteConversation(conversation)
    }
}