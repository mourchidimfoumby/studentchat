package com.example.studentchat.conversation.domain

import com.example.studentchat.conversation.data.Conversation
import com.example.studentchat.conversation.data.ConversationRepository
import kotlinx.coroutines.flow.Flow

class GetAllConversationsUseCase(
    private val conversationRepository: ConversationRepository
) {
    operator fun invoke(): Flow<List<Conversation>> =
        conversationRepository.conversations
}