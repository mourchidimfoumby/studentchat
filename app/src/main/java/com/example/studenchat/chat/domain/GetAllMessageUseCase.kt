package com.example.studenchat.chat.domain

import com.example.studenchat.chat.data.Message
import com.example.studenchat.chat.data.MessageRepository
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.user.domain.GetCurrentUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllMessageUseCase(
    private val messageRepository: MessageRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(
        conversation: Conversation,
        callback: (List<Message>?) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            val currentUser = getCurrentUserUseCase()
            messageRepository.getAllMessage(conversation).collect { messageList ->
                messageList?.map {
                    if(it.author == currentUser.toString())
                        it.author = "Moi"
                }
                withContext(Dispatchers.Main) { callback(messageList) }
            }
        }
    }
}