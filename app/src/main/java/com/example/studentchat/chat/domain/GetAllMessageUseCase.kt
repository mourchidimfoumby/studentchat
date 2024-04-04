package com.example.studentchat.chat.domain

import com.example.data.model.Conversation
import com.example.studentchat.chat.data.Message
import com.example.studentchat.chat.data.MessageRepository
import kotlinx.coroutines.flow.Flow

class GetAllMessageUseCase(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(conversation: Conversation): Flow<Message> =
        messageRepository.getAllMessage(conversation)
}