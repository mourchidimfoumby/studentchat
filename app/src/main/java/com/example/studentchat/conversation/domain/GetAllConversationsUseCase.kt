package com.example.studentchat.conversation.domain

import com.example.data.model.Conversation
import com.example.data.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow

class GetAllConversationsUseCase(
    private val conversationRepository: ConversationRepository
) {
    operator fun invoke(): Flow<List<Conversation>> =
        conversationRepository.conversations
}