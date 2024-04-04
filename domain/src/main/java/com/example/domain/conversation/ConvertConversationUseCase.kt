package com.example.domain.conversation

import android.util.Log
import com.example.data.model.Conversation
import com.example.data.remote.model.ConversationRemote
import com.example.data.repository.MessageRepository
import com.example.data.repository.UserRepository
import com.example.data.toMessage
import com.example.data.toUser
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
                val interlocutors = Pair(first!!.toUser(), second!!.toUser())
                val lastMessage = lastMessageDeffered.await()
                return@withContext Conversation(
                    conversationRemote.id,
                    interlocutors,
                    lastMessage!!.toMessage()
                )
            } catch (e: Exception) {
                Log.e(javaClass.name, "Failed to convert conversation", e)
                return@withContext null
            }
        }

    suspend fun toConversations(conversationsRemoteList: List<ConversationRemote>): List<Conversation>? =
        withContext(Dispatchers.IO) {
            val conversationList = mutableListOf<Conversation>()
            try {
                conversationsRemoteList.forEach { conversationRemote ->
                    val first =
                        userRepository.getUser(conversationRemote.interlocutors.keys.first())
                    val second =
                        userRepository.getUser(conversationRemote.interlocutors.keys.last())
                    val interlocutors = Pair(first!!.toUser(), second!!.toUser())
                    val lastMessage = messageRepository.getMessage(
                        conversationRemote.id,
                        conversationRemote.lastMessage.toLong()
                    )
                    conversationList.add(
                        Conversation(
                            conversationRemote.id,
                            interlocutors,
                            lastMessage!!.toMessage()
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