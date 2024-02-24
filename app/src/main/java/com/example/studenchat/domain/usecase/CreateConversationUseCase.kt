package com.example.studenchat.domain.usecase

<<<<<<< Updated upstream
import com.example.studenchat.data.interfaces.ConversationRepository
import com.example.studenchat.data.source.Conversation

class CreateConversationUseCase (
    private val conversationRepository: ConversationRepository
){
    operator fun invoke(conversation: Conversation){
=======
import com.example.studenchat.data.repository.UserConversationRepository
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.domain.ConversationRepository

class CreateConversationUseCase (
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepository: ConversationRepository
){
    suspend operator fun invoke(conversation: Conversation){
        userConversationRepository.createConversation(conversation)
>>>>>>> Stashed changes
        conversationRepository.createConversation(conversation)
    }
}