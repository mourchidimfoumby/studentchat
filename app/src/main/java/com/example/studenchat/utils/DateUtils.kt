package com.example.studenchat.utils

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
        fun convertDateToString(date: Date): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return dateFormat.format(date)
        }
    }
}