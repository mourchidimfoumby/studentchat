package com.example.studenchat.conversation.domain

import com.example.studenchat.conversation.data.UserConversationRepository
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.conversation.data.ConversationRepositoryImpl

class CreateConversationUseCase (
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepositoryImpl: ConversationRepositoryImpl
){
    suspend operator fun invoke(conversation: Conversation){
        userConversationRepository.createConversation(conversation)
        conversationRepositoryImpl.createConversation(conversation)
    }
}