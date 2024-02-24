package com.example.studenchat.domain.usecase

import com.example.studenchat.data.repository.UserRepository
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.data.source.ConversationDTO

class ConvertConversationDTOUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(conversationDTO: ConversationDTO): Conversation{
        val first = userRepository.getUser(conversationDTO.interlocutors!![0].keys.first())
        val second = userRepository.getUser(conversationDTO.interlocutors[1].keys.first())
        val interlocutors = Pair(first!!,second!!)
        return Conversation(interlocutors, conversationDTO.id, conversationDTO.lastMessage)
    }
    suspend operator fun invoke(conversationsDTOList: List<ConversationDTO>): List<Conversation>{
        val conversationList = mutableListOf<Conversation>()
        conversationsDTOList.forEach { conversationDTO ->
            val first = userRepository.getUser(conversationDTO.interlocutors!![0].keys.first())
            val second = userRepository.getUser(conversationDTO.interlocutors[1].keys.first())
            val interlocutors = Pair(first!!,second!!)
            conversationList.add(
                Conversation(interlocutors,
                    conversationDTO.id,
                    conversationDTO.lastMessage)
            )
        }
        return conversationList
    }
}