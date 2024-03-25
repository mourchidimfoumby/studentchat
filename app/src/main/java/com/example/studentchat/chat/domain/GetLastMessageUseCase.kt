package com.example.studentchat.chat.domain

import com.example.studentchat.chat.data.MessageRepository
import com.example.studentchat.conversation.data.Conversation

class GetLastMessageUseCase(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(conversation: Conversation) {}
}