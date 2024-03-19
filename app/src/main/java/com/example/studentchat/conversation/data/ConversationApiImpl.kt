package com.example.studentchat.conversation.data

import com.example.studentchat.FirebaseApi
import com.example.studentchat.conversation.domain.ConvertConversationUseCase
import com.example.studentchat.utils.TABLE_CONVERSATIONS
import com.example.studentchat.utils.TABLE_USER_CONVERSATIONS
import com.example.studentchat.utils.firebaseDatabase
import com.example.studentchat.utils.userId
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.koin.java.KoinJavaComponent.inject

class ConversationApiImpl : ConversationApi, FirebaseApi {
    private var childEventListener: ChildEventListener? = null
    private var valueEventListener: ValueEventListener? = null
    private val conversationDatabaseReference =
        firebaseDatabase.child(TABLE_CONVERSATIONS)
    private val userConversationDatabaseReference =
        firebaseDatabase.child(TABLE_USER_CONVERSATIONS).child(userId)
    private val convertConversationUseCase: ConvertConversationUseCase by inject(
        ConvertConversationUseCase::class.java
    )

    override fun getAllConversations(): Flow<List<Conversation>> = callbackFlow {
        valueEventListener = userConversationDatabaseReference
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val jobs = snapshot.children.mapNotNull { child ->
                        val conversationId = child.key
                        conversationId?.let {
                            async {
                                getConversation(it)
                            }
                        }
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        trySend(jobs.mapNotNull { it.await() })
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
        awaitClose {}
    }

    override suspend fun getConversation(conversationId: String): Conversation? {
        val conversationDTO = conversationDatabaseReference.child(conversationId)
            .get()
            .await()
            .getValue(ConversationDTO::class.java)
        return conversationDTO?.let {
            it.id = conversationId
            convertConversationUseCase.toConversation(it)
        }
    }

    override suspend fun insertConversation(conversation: Conversation) {
        val conversationDTO = convertConversationUseCase.toConversationDTO(conversation)
        conversationDatabaseReference.child(conversation.id).setValue(conversationDTO)
        userConversationDatabaseReference.child(userId).child(conversation.id).setValue(true)
    }

    override suspend fun updateConversation(conversation: Conversation) {
        val conversationDTO = convertConversationUseCase.toConversationDTO(conversation)
        conversationDatabaseReference.child(conversation.id).setValue(conversationDTO)
    }

    override suspend fun deleteConversation(conversation: Conversation) {
        userConversationDatabaseReference.child(userId).child(conversation.id).removeValue()
        val conversationDTO = conversationDatabaseReference.child(conversation.id)
            .get()
            .await()
            .getValue(ConversationDTO::class.java)
        conversationDTO?.let {
            if (it.isNotActive()) conversationDatabaseReference.child(conversation.id)
                .removeValue()
        }
    }

    override fun removeListener() {
        childEventListener?.let {
            conversationDatabaseReference.removeEventListener(it)
            userConversationDatabaseReference.removeEventListener(it)
        }
        valueEventListener?.let {
            conversationDatabaseReference.removeEventListener(it)
            userConversationDatabaseReference.removeEventListener(it)
        }
    }
}