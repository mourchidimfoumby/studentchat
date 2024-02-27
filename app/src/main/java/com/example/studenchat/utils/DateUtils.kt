package com.example.studenchat.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
const val PATTERN_DAY_MONTH_YEAR = "dd/MM/yyyy"
const val PATTERN_HOURS_MINUTES = "HH:mm"
fun convertDateToString(date: Date, pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(date)
}

fun formatTimestamp(timestamp: Long, pattern: String): String{
    val date = Date(timestamp)
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(date)
}