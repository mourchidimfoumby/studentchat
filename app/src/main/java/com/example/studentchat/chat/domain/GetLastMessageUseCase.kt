package com.example.studentchat.chat.domain

import com.example.data.model.Conversation
import com.example.data.repository.MessageRepository

class GetLastMessageUseCase(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(conversation: Conversation) {}
}