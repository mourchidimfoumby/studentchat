package com.example.domain.chat

import com.example.data.model.Conversation
import com.example.data.model.Message
import com.example.data.repository.MessageRepository
import com.example.data.toRemote

class SendMessageUseCase(private val messageRepository: MessageRepository) {
    suspend operator fun invoke(conversation: Conversation, message: Message) {
        messageRepository.createMessage(conversation.toRemote(), message.toRemote())
    }
}