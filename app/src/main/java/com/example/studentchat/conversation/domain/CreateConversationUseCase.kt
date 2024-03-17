package com.example.studentchat.conversation.domain

import com.example.studentchat.conversation.data.Conversation
import com.example.studentchat.conversation.data.ConversationRepository
import com.example.studentchat.conversation.data.ConversationRepositoryImpl
import com.example.studentchat.conversation.data.UserConversationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateConversationUseCase (
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(conversation: Conversation) {
        withContext(Dispatchers.IO) {
            userConversationRepository.createConversation(conversation)
            conversationRepository.createConversation(conversation)
        }
    }
}