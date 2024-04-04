package com.example.studentchat.chat.domain

import com.example.studentchat.utils.DateUnit
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class FormatTimestampUseCase {
    operator fun invoke(timestamp: Long, dateUnit: DateUnit): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        return when(dateUnit){
            DateUnit.DAY_MONTH_YEAR -> "${dateTime.dayOfMonth} ${dateTime.month} ${dateTime.year}"
            DateUnit.HOUR_MINUTE -> {
                val hour = dateTime.hour.takeIf { it > 9 } ?: "0${dateTime.hour}"
                val minute = dateTime.minute.takeIf { it > 9 } ?: "0${dateTime.minute}"
                "$hour:$minute"
            }
        }
    }
}