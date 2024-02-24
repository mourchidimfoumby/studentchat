package com.example.studenchat.domain.usecase

<<<<<<< Updated upstream
import com.example.studenchat.data.interfaces.ConversationRepository
import com.example.studenchat.data.source.Conversation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetAllConversationUseCase(
    private val conversationRepository: ConversationRepository
) {
    suspend operator fun invoke(): List<Conversation> = suspendCoroutine { continuation ->
        conversationRepository.getAllConversations { continuation.resume(it) }
=======
import android.util.Log
import com.example.studenchat.data.repository.UserConversationRepository
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.domain.ConversationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllConversationUseCase(
    private val userConversationRepository: UserConversationRepository,
    private val conversationRepository: ConversationRepository,
    private val convertConversationDTOUseCase: ConvertConversationDTOUseCase
) {
    suspend operator fun invoke(callback: (List<Conversation>) -> Unit) =
        withContext(Dispatchers.IO){
            try {
                userConversationRepository.getAllIdsConversations().collect {
                    val conversationDTOList = conversationRepository.getConversation(it)
                    val conversationList = convertConversationDTOUseCase(conversationDTOList)
                    withContext(Dispatchers.Main){ callback(conversationList)}
                }
            }
            catch (e: Exception){
                Log.e(javaClass.name,"" ,e)
            }
>>>>>>> Stashed changes
    }
}