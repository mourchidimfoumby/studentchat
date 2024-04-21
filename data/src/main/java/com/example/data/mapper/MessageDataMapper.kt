package com.example.data.mapper

import com.example.data.local.entity.MessageEntity
import com.example.data.model.Message
import com.example.data.remote.model.MessageRemote

internal class MessageDataMapper : DataMapper<MessageEntity, Message, MessageRemote> {
    override suspend fun remoteToLocal(remote: MessageRemote): MessageEntity {
        TODO("Not yet implemented")
    }

    override suspend fun domainToLocal(domain: Message): MessageEntity {
        TODO("Not yet implemented")
    }

    override suspend fun localToRemote(local: MessageEntity): MessageRemote {
        TODO("Not yet implemented")
    }

    override suspend fun localToDomain(local: MessageEntity): Message {
        TODO("Not yet implemented")
    }

}