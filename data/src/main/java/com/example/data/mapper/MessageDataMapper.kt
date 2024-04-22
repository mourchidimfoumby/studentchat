package com.example.data.mapper

import com.example.data.local.entity.MessageEntity
import com.example.data.model.Message
import com.example.data.remote.model.MessageRemote

internal class MessageDataMapper {
    fun remoteToLocal(messageRemote: MessageRemote): MessageEntity {
        TODO("Not yet implemented")
    }

    fun domainToLocal(message: Message): MessageEntity {
        TODO("Not yet implemented")
    }

    fun localToRemote(messageEntity: MessageEntity): MessageRemote {
        TODO("Not yet implemented")
    }

    fun localToDomain(messageEntity: MessageEntity): Message {
        TODO("Not yet implemented")
    }

}