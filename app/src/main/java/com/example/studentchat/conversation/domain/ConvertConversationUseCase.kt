package com.example.studentchat.conversation.domain

import android.util.Log
import com.example.data.model.Conversation
import com.example.data.remote.model.ConversationRemote
import com.example.data.repository.UserRepository
import com.example.data.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ConvertConversationUseCase(
    private val userRepository: UserRepository,
    private val messageRepository: MessageRepository
) {
    suspend fun toConversation(conversationRemote: ConversationRemote): Conversation? =
        withContext(Dispatchers.IO) {
            try {
                val firstDeffered = async {
                    userRepository.getUser(conversationRemote.interlocutors.keys.first())
                }
                val secondDeffered = async {
                    userRepository.getUser(conversationRemote.interlocutors.keys.last())
                }
                val lastMessageDeffered = async {
                    messageRepository.getMessage(
                        conversationRemote.id,
                        conversationRemote.lastMessage.toLong()
                    )
                }
                val first = firstDeffered.await()
                val second = secondDeffered.await()
                val interlocutors = Pair(first!!, second!!)
                val lastMessage = lastMessageDeffered.await()
                return@withContext Conversation(interlocutors, conversationRemote.id, lastMessage!!)
            } catch (e: Exception) {
                Log.e(javaClass.name, "Failed to convert conversation", e)
                return@withContext null
            }
        }

    suspend fun toConversations(conversationsDTOList: List<ConversationRemote>): List<Conversation>? =
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

    suspend fun toConversationDTO(conversation: Conversation): ConversationRemote =
        withContext(Dispatchers.IO) {
            return@withContext ConversationRemote(
                conversation.id,
                mapOf(
                    conversation.interlocutors.first.uid to true,
                    conversation.interlocutors.second.uid to true
                ),
                conversation.lastMessage!!.timestamp.toString()
            )
        }
}