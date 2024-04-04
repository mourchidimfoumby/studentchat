package com.example.domain.chat

import com.example.data.model.Conversation
import com.example.data.model.Message
import com.example.data.repository.MessageRepository
import com.example.data.toMessage
import com.example.data.toRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllMessageUseCase(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(conversation: Conversation): Flow<Message> =
        messageRepository.getAllMessage(conversation.toRemote()).map { it.toMessage() }
}