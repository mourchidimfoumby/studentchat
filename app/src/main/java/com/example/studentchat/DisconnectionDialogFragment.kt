package com.example.studentchat

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.authentication.domain.LogOutUseCase
import com.example.authentication.ui.AuthenticationActivity
import org.koin.android.ext.android.inject

class DisconnectionDialogFragment: DialogFragment() {
    private val logOutUseCase: LogOutUseCase by inject()
    private val sharedPreferencesUseCase: SharedPreferencesUseCase by inject()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("Voulez-vous vraiment vous déconnecter ?")
                .setPositiveButton("Oui") { _, _ ->
                    sharedPreferencesUseCase.putBoolean(SharedPreferencesTag.APP_IS_LAUNCHED.name, false)
                    logOutUseCase()
                    requireContext().launchActivity(
                        activityClass = AuthenticationActivity::class.java,
                        intentFeatures = {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                    )
                }
                .setNegativeButton("Annuler"){ dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("L'activité ne peut pas etre nulle")
    }
}