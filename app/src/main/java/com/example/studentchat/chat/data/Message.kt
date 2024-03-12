package com.example.studentchat.chat.data

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Message(
    var author: String = "",
    val text: String = "",
    var datetime: Long = 0,
): Serializable {
    @Exclude
    override fun equals(other: Any?): Boolean {
        other as Message
        return this.author == other.author &&
                this.text == other.text &&
                this.datetime == other.datetime
    }

    override fun toString(): String = text
}