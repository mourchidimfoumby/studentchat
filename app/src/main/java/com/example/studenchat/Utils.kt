package com.example.studenchat

import com.example.studenchat.model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

val URL_DATABASE = "https://studentchat-a99ae-default-rtdb.europe-west1.firebasedatabase.app/"

fun inputIsNotEmpty(inputList: List<TextInputEditText>): Boolean {
    inputList.forEach {
        if (it.text!!.isBlank()) {
            return false
        }
    }
    return true
}

fun convertDateToString(date: Date): String{
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(date)
}

fun convertDateTimeToString(dateTime: LocalDateTime): String{
    val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return dateFormat.format(dateTime)
}

fun getHoursFromStringDateTime(dateTimeString: String): String{
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val dateTime = convertStringToDateTime(dateTimeString)
    return dateFormat.format(dateTime)
}

fun getDateFromStringDateTime(dateTimeString: String): String{
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dateTime = convertStringToDate(dateTimeString)
    return dateFormat.format(dateTime)
}

private fun convertStringToDateTime(dateTimeString: String): Date{
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return dateFormat.parse(dateTimeString) ?: Date()
}
private fun convertStringToDate(dateString: String): Date{
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.parse(dateString)!!
}