package com.example.studenchat.conversation.domain

import android.util.Log
import com.example.studenchat.chat.data.MessageRepository
import com.example.studenchat.conversation.data.Conversation
import com.example.studenchat.conversation.data.ConversationDTO
import com.example.studenchat.user.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConvertConversationDTOUseCase(
    private val userRepository: UserRepository,
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(conversationDTO: ConversationDTO): Conversation? {
        return withContext(Dispatchers.IO) {
            try {
                val first = userRepository.getUser(conversationDTO.interlocutors!![0].keys.first())
                val second = userRepository.getUser(conversationDTO.interlocutors[1].keys.first())
                val interlocutors = Pair(first!!, second!!)
                val lastMessage = conversationDTO.lastMessage?.let {
                    messageRepository.getMessage(conversationDTO.id, it)
                }
                return@withContext Conversation(interlocutors, conversationDTO.id, lastMessage)
            } catch (e: Exception) {
                Log.e(javaClass.name, "Failed to convert conversation", e)
                return@withContext null
            }
        }
    }
    suspend operator fun invoke(conversationsDTOList: List<ConversationDTO>): List<Conversation>? {
        return withContext(Dispatchers.IO) {
            val conversationList = mutableListOf<Conversation>()
            try {
                conversationsDTOList.forEach { conversationDTO ->
                    val first = conversationDTO.interlocutors?.get(0)?.keys?.let {
                        userRepository.getUser(it.first())
                    }
                    val second = conversationDTO.interlocutors?.get(1)?.keys?.let {
                        userRepository.getUser(
                            it.first()
                        )
                    }
                    val interlocutors = Pair(first!!, second!!)
                    val lastMessage = conversationDTO.lastMessage?.let {
                        messageRepository.getMessage(conversationDTO.id, it)
                    }
                    conversationList.add(
                        Conversation(
                            interlocutors,
                            conversationDTO.id,
                            lastMessage
                        )
                    )
                }
                return@withContext conversationList
            } catch (e: Exception) {
                Log.e(javaClass.name, "Failed to convert conversation", e)
                return@withContext null
            }
        }
    }
}