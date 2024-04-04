package com.example.data

import com.example.data.model.Conversation
import com.example.data.model.Message
import com.example.data.model.User
import com.example.data.remote.model.ConversationRemote
import com.example.data.remote.model.MessageRemote
import com.example.data.remote.model.UserRemote

fun UserRemote.toUser() = User(
    this.uid,
    this.firstName,
    this.lastName,
    this.mail
)

fun User.toRemote() = UserRemote(
    this.uid,
    this.firstName,
    this.lastName,
    this.mail
)


fun MessageRemote.toMessage() = Message(
    this.author,
    this.text,
    this.timestamp
)

fun Message.toRemote() = MessageRemote(
    this.author,
    this.text,
    this.timestamp
)

fun Conversation.toRemote() = ConversationRemote(
    this.id,
    mapOf(
        this.interlocutors.first.uid to true,
        this.interlocutors.second.uid to true
    ),
    this.lastMessage.timestamp.toString()
)