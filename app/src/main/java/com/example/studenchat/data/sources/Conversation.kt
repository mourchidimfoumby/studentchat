package com.example.studenchat.data.sources

import com.example.studenchat.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.Exclude
import com.google.firebase.ktx.Firebase

data class Conversation(
    val interlocutors: Pair<User,User>,
){
    var id: String? = null
    var messages: List<Message>? = null
    var title: String
    var picture: Int

    @Exclude
    private fun otherUser(): User {
        return if(interlocutors.first.uid == Firebase.auth.uid) interlocutors.second
        else interlocutors.first
    }

    init {
        title = otherUser().toString()
        picture = R.drawable.ic_avatar
    }
}

data class ConversationGroup(
    val listInterlocutors: MutableList<User>
){
    var id: String? = null
    var messages: List<Message>? = null
    var title: String = defaultTitle()
    var picture: Int = R.drawable.ic_group

    @Exclude
    private fun defaultTitle(): String{
        val fullTitle = ""
        listInterlocutors.forEach {
            fullTitle.plus("$it, ")
        }
        fullTitle.dropLast(2)
        return fullTitle
    }
}