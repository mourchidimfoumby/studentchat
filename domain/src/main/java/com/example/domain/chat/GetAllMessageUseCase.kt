package com.example.domain.chat

import com.example.data.model.Conversation
import com.example.data.model.Message
import com.example.data.repository.MessageRepository
import kotlinx.coroutines.flow.Flow

class GetAllMessageUseCase(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(conversation: Conversation): Flow<Message> = TODO()
//        messageRepository.getAllMessage(conversation.toRemote()).map { it.toMessage() }
}