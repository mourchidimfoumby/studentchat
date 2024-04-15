package com.example.data.model

import com.example.data.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import java.io.Serializable

data class Conversation(
    val id: String,
    val interlocutors: Pair<User, User>,
    var lastMessage: Message,
    var picture: Int = R.drawable.ic_avatar
) : Serializable {
    fun otherUser(): User {
        return if (interlocutors.first.uid == Firebase.auth.uid) interlocutors.second
        else interlocutors.first
    }

    fun currentUser(): User {
        return if (interlocutors.first.uid == Firebase.auth.uid) interlocutors.first
        else interlocutors.second
    }

    override fun equals(other: Any?): Boolean {
        other as Conversation
        return this.picture == other.picture &&
                this.lastMessage == other.lastMessage &&
                this.interlocutors == other.interlocutors
    }

    override fun hashCode(): Int = id.hashCode()

}