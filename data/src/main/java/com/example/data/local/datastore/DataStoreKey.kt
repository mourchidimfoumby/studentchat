package com.example.data.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

internal sealed class DataStoreKey {
    object User {
        val UID = stringPreferencesKey("uid")
        val FIRST_NAME = stringPreferencesKey("first_name")
        val LAST_NAME = stringPreferencesKey("last_name")
        val MAIL = stringPreferencesKey("mail")
        val BIRTHDAY = stringPreferencesKey("birthday")
        val PASSWORD = stringPreferencesKey("password")
    }
}