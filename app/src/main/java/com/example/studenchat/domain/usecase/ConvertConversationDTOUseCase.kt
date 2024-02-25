package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.MessageRepository
import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.data.source.ConversationDTO
import com.example.studenchat.data.source.Message

class ConvertConversationDTOUseCase(
    private val userRepository: UserRepository,
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(conversationDTO: ConversationDTO): Conversation{
        val first = userRepository.getUser(conversationDTO.interlocutors!![0].keys.first())
        val second = userRepository.getUser(conversationDTO.interlocutors[1].keys.first())
        val interlocutors = Pair(first!!,second!!)
        val lastMessage = conversationDTO.lastMessage?.let { messageRepository.getMessage(it) }?: Message()
        return Conversation(interlocutors, conversationDTO.id, lastMessage)
    }
    suspend operator fun invoke(conversationsDTOList: List<ConversationDTO>): List<Conversation>{
        val conversationList = mutableListOf<Conversation>()
        conversationsDTOList.forEach { conversationDTO ->
            val first = userRepository.getUser(conversationDTO.interlocutors!![0].keys.first())
            val second = userRepository.getUser(conversationDTO.interlocutors[1].keys.first())
            val interlocutors = Pair(first!!,second!!)
            val lastMessage = conversationDTO.lastMessage?.let { messageRepository.getMessage(it) }?: Message()
            conversationList.add(
                Conversation(interlocutors,
                    conversationDTO.id,
                    lastMessage)
            )
        }
        return conversationList
    }
}