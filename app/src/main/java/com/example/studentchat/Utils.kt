package com.example.studentchat

import android.content.Context
import android.content.Intent

fun <T>Context.launchActivity(
    activityClass: Class<T>,
    contextFeatures: (Context.() -> Unit)? = null,
    intentFeatures: (Intent.() -> Unit)? = null
) {
    Intent(this, activityClass).also { intent ->
        intentFeatures?.let {
            intent.it()
        }
        startActivity(intent)
        contextFeatures?.let {
            this.it()
        }
    }
}