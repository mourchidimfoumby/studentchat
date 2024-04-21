package com.example.data.model

import com.example.data.R
import java.io.Serializable

data class Conversation(
    val id: String,
    val interlocutor: Friends,
    var lastMessage: Message,
    var picture: Int = R.drawable.ic_avatar
) : Serializable {

    override fun equals(other: Any?): Boolean {
        other as Conversation
        return this.interlocutor == other.interlocutor
    }

    override fun hashCode(): Int = id.hashCode()
}