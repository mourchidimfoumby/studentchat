package com.example.data.model

import java.io.Serializable

data class Message(
    var authorId: String,
    val text: String,
    var timestamp: Long,
    val conversationId: String,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        other as Message
        return this === other || (this.authorId == other.authorId &&
                this.text == other.text &&
                this.timestamp == other.timestamp)
    }

    override fun toString(): String = text
    override fun hashCode(): Int {
        var result = authorId.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}