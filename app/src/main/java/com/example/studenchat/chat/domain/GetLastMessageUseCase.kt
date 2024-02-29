package com.example.studenchat.chat.domain

import com.example.studenchat.chat.data.Message
import com.example.studenchat.chat.data.MessageRepository
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.user.domain.GetCurrentUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetLastMessageUseCase(
    private val messageRepository: MessageRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    private val convertTimestampMessageUseCase = ConvertTimestampMessageUseCase()
    suspend operator fun invoke(conversation: Conversation, callback: (Message?) -> Unit) {
        messageRepository.getLastMessage(conversation).collect { message ->
            if (message != null) {
                withContext(Dispatchers.IO) {
                    val currentUser = getCurrentUserUseCase()
                    message.apply {
                        convertTimestampMessageUseCase(this)
                        if (author == currentUser.toString()) {
                            author = "Moi"
                        }
                    }
                    withContext(Dispatchers.Main) { callback(message) }
                }
            }
        }
    }
}