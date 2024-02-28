package com.example.studenchat.chat.domain

import com.example.studenchat.chat.data.MessageRepository
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.chat.data.Message
import com.example.studenchat.utils.PATTERN_HOURS_MINUTES
import com.example.studenchat.utils.formatTimestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetAllMessageUseCase(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(
        conversation: Conversation,
        callback: (List<Message>) -> Unit
    ) {
        messageRepository.getAllMessage(conversation).collect { messageList ->
            if(messageList == null) return@collect
            CoroutineScope(Dispatchers.IO).launch {
                    messageList.map {
                        it.dateTime = formatTimestamp(
                            it.dateTime.toLong(),
                            PATTERN_HOURS_MINUTES
                        )
                    }
                    withContext(Dispatchers.Main) { callback(messageList) }
                }.join()
            }
    }
}