package com.example.domain.conversation

import com.example.data.model.Conversation
import com.example.data.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllConversationsUseCase(
    private val conversationRepository: ConversationRepository,
    private val convertConversationUseCase: ConvertConversationUseCase
) {
    operator fun invoke(): Flow<List<Conversation>> =
        conversationRepository.conversations.map { conversationList ->
            conversationList.mapNotNull { convertConversationUseCase.toConversation(it) }
        }
}