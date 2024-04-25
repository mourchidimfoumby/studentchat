package com.example.domain.chat

import com.example.data.model.Message
import com.example.data.repository.MessageRepository

class SendMessageUseCase(private val messageRepository: MessageRepository) {
    suspend operator fun invoke(message: Message) {
        messageRepository.createMessage(message)
    }
}