package com.example.studentchat

import android.content.Context
import android.content.Intent
import com.example.studentchat.sharedpreferences.SharedPreferencesName
import com.example.studentchat.sharedpreferences.SharedPreferencesTag
import com.example.studentchat.sharedpreferences.SharedPreferencesUseCase
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class AuthenticationListenerUseCase(private val context: Context) {
    private val sharedPreferencesUseCase: SharedPreferencesUseCase by inject(
        SharedPreferencesUseCase::class.java) {
        parametersOf(SharedPreferencesName.APP.name)
    }
    operator fun invoke(){
        val appIsLaunched = sharedPreferencesUseCase.getBoolean(SharedPreferencesTag.APP_IS_LAUNCHED.name, false)
        FirebaseAuth.getInstance().addAuthStateListener {
            if(it.currentUser != null && !appIsLaunched){
                context.launchActivity(
                    activityClass = MainActivity::class.java,
                    intentFeatures = {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                )
            }
        }
    }
}