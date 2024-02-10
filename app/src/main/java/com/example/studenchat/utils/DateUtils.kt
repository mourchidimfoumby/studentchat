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

        fun convertDateTimeToString(dateTime: LocalDateTime): String {
            val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            return dateFormat.format(dateTime)
        }

        fun getTimeFromDateTime(dateTimeString: String): String {
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val dateTime = convertStringToDateTime(dateTimeString)
            return dateFormat.format(dateTime)
        }

        fun getDateFromDateTime(dateTimeString: String): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateTime = convertStringToDate(dateTimeString)
            return dateFormat.format(dateTime)
        }
        private fun convertStringToDateTime(dateTimeString: String): Date {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            return dateFormat.parse(dateTimeString) ?: Date()
        }

        private fun convertStringToDate(dateString: String): Date {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return dateFormat.parse(dateString)!!
        }
    }
}