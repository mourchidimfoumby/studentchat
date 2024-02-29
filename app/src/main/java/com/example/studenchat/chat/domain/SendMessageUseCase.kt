package com.example.studenchat.chat.domain

import com.example.studenchat.chat.data.Message
import com.example.studenchat.chat.data.MessageRepository
import com.example.studenchat.conversation.data.Conversation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SendMessageUseCase(private val messageRepository: MessageRepository) {
    suspend operator fun invoke(conversation: Conversation, message: Message) {
        withContext(Dispatchers.IO) {
            messageRepository.createMessage(conversation, message)
        }
    }
}