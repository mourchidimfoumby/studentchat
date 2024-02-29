package com.example.studenchat.chat.domain

import com.example.studenchat.chat.data.Message
import com.example.studenchat.utils.PATTERN_HOURS_MINUTES
import com.example.studenchat.utils.formatTimestamp

class ConvertTimestampMessageUseCase {
    operator fun invoke(message: Message) {
        message.apply {
            dateTime = formatTimestamp(
                dateTime.toLong(),
                PATTERN_HOURS_MINUTES
            )
        }
    }
}