package com.example.data.mapper

import com.example.data.local.entity.MessageEntity
import com.example.data.model.Message
import com.example.data.remote.model.MessageRemote

internal class MessageDataMapper {
    fun remoteToLocal(messageRemote: MessageRemote): MessageEntity =
        MessageEntity(
            authorId = messageRemote.author,
            text = messageRemote.text,
            timestamp = messageRemote.timestamp,
            conversationId = messageRemote.conversationId
        )

    fun domainToLocal(message: Message): MessageEntity =
        MessageEntity(
            authorId = message.authorId,
            text = message.text,
            timestamp = message.timestamp,
            conversationId = message.conversationId
        )

    fun localToRemote(messageEntity: MessageEntity): MessageRemote =
        MessageRemote(
            author = messageEntity.authorId,
            text = messageEntity.text,
            timestamp = messageEntity.timestamp,
            conversationId = messageEntity.conversationId
        )

    fun localToDomain(messageEntity: MessageEntity): Message =
        Message(
            authorId = messageEntity.authorId,
            text = messageEntity.text,
            timestamp = messageEntity.timestamp,
            conversationId = messageEntity.conversationId
        )

}