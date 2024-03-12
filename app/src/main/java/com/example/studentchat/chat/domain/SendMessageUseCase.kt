package com.example.studentchat.chat.domain

import com.example.studentchat.chat.data.Message
import com.example.studentchat.chat.data.MessageRepository
import com.example.studentchat.conversation.data.Conversation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SendMessageUseCase(private val messageRepository: MessageRepository) {
    suspend operator fun invoke(conversation: Conversation, message: Message) {
        withContext(Dispatchers.IO) {
            messageRepository.createMessage(conversation, message)
        }
    }
}