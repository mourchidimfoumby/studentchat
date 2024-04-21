package com.example.domain.conversation

import com.example.data.model.Conversation
import com.example.data.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow

class GetAllConversationsUseCase(
    private val conversationRepository: ConversationRepository,
) {
    operator fun invoke(): Flow<List<Conversation>> =
        conversationRepository.getAllConversations()
}