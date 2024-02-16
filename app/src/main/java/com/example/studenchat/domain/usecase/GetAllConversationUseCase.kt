package com.example.studenchat.domain.usecase

import com.example.studenchat.data.interfaces.ConversationRepository
import com.example.studenchat.data.source.Conversation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetAllConversationUseCase(
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(): List<Conversation> = suspendCoroutine { continuation ->
        conversationRepository.getAllConversations { continuation.resume(it) }
    }
}