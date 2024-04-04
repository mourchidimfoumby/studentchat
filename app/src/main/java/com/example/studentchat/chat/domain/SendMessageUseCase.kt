package com.example.studentchat.chat.domain

import com.example.data.model.Conversation
import com.example.data.model.Message
import com.example.data.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SendMessageUseCase(private val messageRepository: MessageRepository) {
    suspend operator fun invoke(conversation: Conversation, message: Message) {
        withContext(Dispatchers.IO) {
            messageRepository.createMessage(conversation, message)
        }
    }
}