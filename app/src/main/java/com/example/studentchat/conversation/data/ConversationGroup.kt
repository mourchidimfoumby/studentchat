package com.example.studentchat.conversation.data

import com.example.studentchat.R
import com.example.studentchat.chat.data.Message
import com.example.studentchat.user.data.User
import com.google.firebase.database.Exclude

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