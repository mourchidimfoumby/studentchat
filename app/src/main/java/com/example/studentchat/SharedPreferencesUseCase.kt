package com.example.studentchat

import android.content.Context

class SharedPreferencesUseCase(context: Context, filePreference: String) {
    private val sharedPreference = context.getSharedPreferences(filePreference, Context.MODE_PRIVATE)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        sharedPreference.getBoolean(key, defaultValue)
    fun putBoolean(key: String, value: Boolean){
        sharedPreference.edit().putBoolean(key, value).commit()
    }
}