package com.example.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
const val PATTERN_DAY_MONTH_YEAR = "dd/MM/yyyy"
fun convertDateToString(date: Date, pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(date)
}

enum class DateUnit {
    HOUR_MINUTE,
    DAY_MONTH_YEAR
}