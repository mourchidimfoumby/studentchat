package com.example.domain.chat

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class TimestampToLocalDateUseCase {
    operator fun invoke(timestamp: Long): LocalDate {
        val instant = Instant.ofEpochSecond(timestamp)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        return LocalDate.of(dateTime.year, dateTime.month, dateTime.dayOfMonth)
    }
}