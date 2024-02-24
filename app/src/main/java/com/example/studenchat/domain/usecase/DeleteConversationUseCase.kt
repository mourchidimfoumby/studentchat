package com.example.studenchat.domain.usecase

<<<<<<< Updated upstream
import com.example.studenchat.data.interfaces.ConversationRepository
import com.example.studenchat.data.source.Conversation

class DeleteConversationUseCase(
    private val conversationRepository: ConversationRepository,
) {
    operator fun invoke(conversation: Conversation){
        conversationRepository.deleteConversation(conversation)
=======
import com.example.studenchat.data.repository.UserConversationRepository
import com.example.studenchat.data.source.Conversation

class DeleteConversationUseCase(
    private val userConversationRepository: UserConversationRepository,
) {
    suspend operator fun invoke(conversation: Conversation){
        userConversationRepository.deleteConversation(conversation)
>>>>>>> Stashed changes
    }
}