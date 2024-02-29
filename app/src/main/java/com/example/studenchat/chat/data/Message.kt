package com.example.studenchat.chat.data

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Message(
    var author: String = "",
    val text: String = "",
    var dateTime: String = "",
): Serializable {
    @Exclude
    override fun equals(other: Any?): Boolean {
        other as Message
        return this.author == other.author &&
                this.text == other.text &&
                this.dateTime == other.dateTime
    }

    override fun toString(): String = text
}