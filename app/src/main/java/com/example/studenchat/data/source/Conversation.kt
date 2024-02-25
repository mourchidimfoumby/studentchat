package com.example.studenchat.data.source

import com.example.studenchat.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.Exclude

data class Conversation(
    val interlocutors: Pair<User, User>? = null,
    var id: String = "",
    var lastMessage: Message? = null
) {
    @get:Exclude
    @set:Exclude
    var picture: Int = R.drawable.ic_avatar

    @Exclude
    fun otherUser(): User {
        return if (interlocutors != null) {
            if (interlocutors.first.uid == Firebase.auth.uid) interlocutors.second
            else interlocutors.first
        } else User()
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

data class ConversationDTO(
    val interlocutors: List<Map<String, Boolean>>? = null,
    var id: String = "",
    var lastMessage: String? = null
)