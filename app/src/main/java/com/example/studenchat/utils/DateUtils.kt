package com.example.studenchat.utils

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale
const val PATTERN_DAY_MONTH_YEAR = "dd/MM/yyyy"
fun convertDateToString(date: Date, pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(date)
}
enum class UNIT{
    HOUR_MINUTE,
    DAY_MONTH_YEAR
}