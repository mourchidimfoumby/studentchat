package com.example.authentication

import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val PATTERN_DAY_MONTH_YEAR = "dd/MM/yyyy"
fun inputIsEmpty(inputList: List<TextInputEditText>): Boolean {
    inputList.forEach {
        if (it.text!!.isBlank()) {
            return true
        }
    }
    return false
}

fun convertDateToString(date: Date, pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(date)
}