package com.example.studenchat.utils

import com.google.android.material.textfield.TextInputEditText

fun inputIsEmpty(inputList: List<TextInputEditText>): Boolean {
    inputList.forEach {
        if (it.text!!.isBlank()) {
            return true
        }
    }
    return false
}