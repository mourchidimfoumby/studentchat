package com.example.studenchat.domain.usecase

import android.util.Log
import com.example.studenchat.data.repository.MessageRepository
import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.data.source.ConversationDTO
import com.example.studenchat.data.source.Message

class ConvertConversationDTOUseCase(
    private val userRepository: UserRepository,
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(conversationDTO: ConversationDTO): Conversation? {
        try {
            val first = userRepository.getUser(conversationDTO.interlocutors!![0].keys.first())
            val second = userRepository.getUser(conversationDTO.interlocutors[1].keys.first())
            val interlocutors = Pair(first!!, second!!)
            val lastMessage = conversationDTO.lastMessage?.let {
                messageRepository.getMessage(conversationDTO.id, it)
            }
            return Conversation(interlocutors, conversationDTO.id, lastMessage)
        }
        catch (e: Exception){
            Log.e(javaClass.name, "Failed to convert conversation", e)
            return null
        }
    }
    suspend operator fun invoke(conversationsDTOList: List<ConversationDTO>): List<Conversation>? {
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
            return conversationList
        } catch (e: Exception) {
            Log.e(javaClass.name, "Failed to convert conversation", e)
            return null
        }
    }
}