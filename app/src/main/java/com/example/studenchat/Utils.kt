package com.example.studenchat

import com.google.android.material.textfield.TextInputEditText

fun inputIsNotEmpty(inputList: List<TextInputEditText>): Boolean {
    inputList.forEach {
        if (it.text!!.isBlank()) {
            return false
        }
    }
    return true
}