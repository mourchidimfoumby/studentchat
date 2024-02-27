package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.UserConversationRepository
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.domain.ConversationRepository

class CreateConversationUseCase (
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepository: ConversationRepository
){
    suspend operator fun invoke(conversation: Conversation){
        userConversationRepository.createConversation(conversation)
        conversationRepository.createConversation(conversation)
    }
}