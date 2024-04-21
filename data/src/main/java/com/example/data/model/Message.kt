package com.example.data.model

import java.io.Serializable

data class Message(
    var author: String,
    val text: String,
    var timestamp: Long,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        other as Message
        return this === other || (this.author == other.author &&
                this.text == other.text &&
                this.timestamp == other.timestamp)
    }

    override fun toString(): String = text
    override fun hashCode(): Int {
        var result = author.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}