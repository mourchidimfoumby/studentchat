package com.example.studentchat.chat.domain

import com.example.studentchat.chat.data.Message
import com.example.studentchat.chat.data.MessageRepository
import com.example.studentchat.conversation.data.Conversation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetLastMessageUseCase(
    private val messageRepository: MessageRepository,
) {
    suspend operator fun invoke(conversation: Conversation, callback: (Message?) -> Unit) {
        messageRepository.getLastMessage(conversation).collect { message ->
            if (message != null) {
                withContext(Dispatchers.IO) {
                    message.apply {
                        if (author == conversation.currentUser().toString()) {
                            author = "Moi"
                        }
                    }
                    withContext(Dispatchers.Main) { callback(message) }
                }
            }
        }
    }
}