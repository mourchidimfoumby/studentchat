package com.example.studentchat.conversation.data

import com.example.studentchat.R
import com.example.studentchat.chat.data.Message
import com.example.studentchat.user.data.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.Exclude
import java.io.Serializable

data class Conversation(
    val interlocutors: Pair<User, User>? = null,
    var id: String = "",
    var lastMessage: Message? = null
): Serializable {
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

    @Exclude
    fun currentUser(): User {
        return if (interlocutors!!.first.uid == Firebase.auth.uid) interlocutors.first
        else interlocutors.second
    }
    override fun equals(other: Any?): Boolean {
        other as Conversation
        return this.picture == other.picture &&
                this.lastMessage == other.lastMessage &&
                this.interlocutors == other.interlocutors
    }
}