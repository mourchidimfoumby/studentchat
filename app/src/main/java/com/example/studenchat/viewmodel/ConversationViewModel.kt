package com.example.studenchat.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studenchat.repository.FirebaseParameters
import com.example.studenchat.model.Conversation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ConversationViewModel: ViewModel(), FirebaseParameters{
    val TAG: String = ConversationViewModel::class.java.name
    private val userId = auth.uid
    private val _conversations = MutableLiveData<List<Conversation>>()
    val conversation: LiveData<List<Conversation>> = _conversations
    override val tableName = "user-conversations"

    init {
        try {
            getAllConversations()
        }catch (e: Exception){
            Log.i(TAG, "L'utilisateur n'a pas de conversation")
        }
    }
    private fun getAllConversations() {
        firebaseDatabase.child(tableName).child(userId!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val conversationsList = mutableListOf<Conversation>()
                for (result in snapshot.children){
                    val conversation = result.getValue(Conversation::class.java)
                    conversation?.let { conversationsList.add(it)}
                }
                _conversations.value = conversationsList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, error.message)
            }
        })
    }

    fun addConversation(conversation: Conversation) {
        val key = firebaseDatabase.child("conversations").push().key
        if(key == null){
            Log.w(TAG, "Couldn't get push key for conversations")
            return
        }
        conversation.id = key
        val childUpdates = hashMapOf<String, Any>(
            "/conversation/$key" to conversation,
            "/$tableName/$userId/$key" to conversation
        )
        firebaseDatabase.updateChildren(childUpdates).addOnFailureListener {error ->
            Log.e(TAG, error.message!!)
        }
    }

    fun removeConversation(conversationId: String) {
        val childRemove = hashMapOf<String, Any?>(
            "/conversation/$conversationId" to null,
            "/$tableName/$userId/$conversationId" to null
        )
        firebaseDatabase.updateChildren(childRemove).addOnFailureListener {error ->
            Log.e(TAG, error.message!!)
        }
    }

}