package com.example.studenchat.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.studenchat.authentication.AuthenticationActivity
import com.example.studenchat.authentication.domain.LogOutUseCase
import org.koin.java.KoinJavaComponent.inject

class DisconnectionDialogFragment: DialogFragment() {
    private val logOutUseCase: LogOutUseCase by inject(LogOutUseCase::class.java)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("Voulez-vous vraiment vous déconnecter ?")
                .setPositiveButton("Se déconnecter"){ _, _ ->
                    Intent(context, AuthenticationActivity::class.java).also{
                        logOutUseCase()
                        startActivity(it)
                        activity.finish()
                    }
                }
                .setNegativeButton("Annuler"){ dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("L'activité ne peut pas etre nulle")
    }
}