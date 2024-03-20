package com.example.studentchat.conversation.domain

import android.util.Log
import com.example.studentchat.chat.data.MessageRepository
import com.example.studentchat.conversation.data.Conversation
import com.example.studentchat.conversation.data.ConversationDTO
import com.example.studentchat.user.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConvertConversationUseCase(
    private val userRepository: UserRepository,
    private val messageRepository: MessageRepository
) {
    suspend fun toConversation(conversationDTO: ConversationDTO): Conversation? =
        withContext(Dispatchers.IO) {
            try {
                val first = userRepository.getUser(conversationDTO.interlocutors.keys.first())
                val second = userRepository.getUser(conversationDTO.interlocutors.keys.last())
                val interlocutors = Pair(first!!, second!!)
                val lastMessage = messageRepository.getMessage(
                    conversationDTO.id,
                    conversationDTO.lastMessage.toLong()
                )
                return@withContext Conversation(interlocutors, conversationDTO.id, lastMessage!!)
            } catch (e: Exception) {
                Log.e(javaClass.name, "Failed to convert conversation", e)
                return@withContext null
            }
        }

    suspend fun toConversations(conversationsDTOList: List<ConversationDTO>): List<Conversation>? =
        withContext(Dispatchers.IO) {
            val conversationList = mutableListOf<Conversation>()
            try {
                conversationsDTOList.forEach { conversationDTO ->
                    val first = userRepository.getUser(conversationDTO.interlocutors.keys.first())
                    val second = userRepository.getUser(conversationDTO.interlocutors.keys.last())
                    val interlocutors = Pair(first!!, second!!)
                    val lastMessage = messageRepository.getMessage(
                        conversationDTO.id,
                        conversationDTO.lastMessage.toLong()
                    )
                    conversationList.add(
                        Conversation(
                            interlocutors,
                            conversationDTO.id,
                            lastMessage!!
                        )
                    )
                }
                return@withContext conversationList
            } catch (e: Exception) {
                Log.e(javaClass.name, "Failed to convert conversation", e)
                return@withContext null
            }
        }

    suspend fun toConversationDTO(conversation: Conversation): ConversationDTO =
        withContext(Dispatchers.IO) {
            return@withContext ConversationDTO(
                conversation.id,
                mapOf(
                    conversation.interlocutors.first.uid to true,
                    conversation.interlocutors.second.uid to true
                ),
                conversation.lastMessage.timestamp.toString()
            )
        }
}