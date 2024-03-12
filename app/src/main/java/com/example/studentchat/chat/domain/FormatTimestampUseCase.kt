package com.example.studentchat.chat.domain

import com.example.studentchat.utils.UNIT
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class FormatTimestampUseCase {
    operator fun invoke(timestamp: Long, unit: UNIT): String{
        val instant = Instant.ofEpochSecond(timestamp)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        return when(unit){
            UNIT.DAY_MONTH_YEAR -> "${dateTime.dayOfMonth} ${dateTime.month} ${dateTime.year}"
            UNIT.HOUR_MINUTE -> "${dateTime.hour}:${dateTime.minute}"
        }
    }
}