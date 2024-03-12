package com.example.studentchat.utils

import android.content.Context
import android.widget.Toast

fun Context.clickToast(element: String) {
    Toast.makeText(this, "Click sur $element", Toast.LENGTH_SHORT).show()
}

fun Context.messageToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}