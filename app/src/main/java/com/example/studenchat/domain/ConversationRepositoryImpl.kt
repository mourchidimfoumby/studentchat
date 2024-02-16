package com.example.studenchat.domain

import android.util.Log
import com.example.studenchat.data.interfaces.ConversationRepository
import com.example.studenchat.data.source.Conversation
import com.example.studenchat.data.source.ConversationGroup
import com.example.studenchat.data.source.User
import com.example.studenchat.utils.FirebaseUtils.Companion.userId
import com.example.studenchat.utils.TABLE_CONVERSATION
import com.example.studenchat.utils.TABLE_USER
import com.example.studenchat.utils.URL_DATABASE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ConversationRepositoryImpl: ConversationRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance(URL_DATABASE).reference
    override fun getAllConversations(onDataChangeCallback:(List<Conversation>) -> Unit){
            firebaseDatabase.child(TABLE_CONVERSATION).child(userId!!).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val conversationsList = mutableListOf<Conversation>()
                    for (result in snapshot.children) {
                        val conversation = result.getValue(Conversation::class.java)
                        conversation?.let { conversationsList.add(it) }
                    }
                    onDataChangeCallback(conversationsList)
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d(javaClass.name, error.message)
                }
            })
        }

    override fun createConversation(conversation: Conversation) {
        val key = firebaseDatabase.child("conversation").push().key
        if (key == null) {
            Log.w(this.javaClass.name, "Couldn't get push key for conversation")
        }
        conversation.id = key!!
        val childUpdates = hashMapOf(
            "/$TABLE_CONVERSATION/$key" to conversation,
            "/$TABLE_USER/$userId/$TABLE_CONVERSATION/$key" to true
        )
        firebaseDatabase.updateChildren(childUpdates)
            .addOnFailureListener { error ->
                Log.e(this.javaClass.name, error.message!!)
            }
    }

    override fun deleteConversation(conversation: Conversation) {
        val childRemove = hashMapOf<String, Any?>(
            "/conversation/${conversation.id}" to null,
            "/$TABLE_CONVERSATION/$userId/${conversation.id}" to null
        )
        firebaseDatabase.updateChildren(childRemove)
            .addOnFailureListener { error ->
                Log.e(this.javaClass.name, error.message!!)
            }
    }

    override fun modifyTitle(title: String, conversation: Conversation) {
        firebaseDatabase
            .child("conversation")
            .child(conversation.id!!)
            .child("title")
            .setValue(title)
            .addOnFailureListener {
                Log.e(javaClass.name, it.message.toString())
            }
    }

    override fun addUser(user: User, conversationGroup: ConversationGroup) {
        TODO("Not yet implemented")
    }

    override fun removeUser(user: User, conversationGroup: ConversationGroup) {
        TODO("Not yet implemented")
    }


}