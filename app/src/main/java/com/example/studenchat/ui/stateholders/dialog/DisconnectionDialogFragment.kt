package com.example.studenchat.ui.stateholders.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.studenchat.authentication.domain.LogOutUseCase
import com.example.studenchat.authentication.ui.AuthenticationActivity

class DisconnectionDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("Se déconnecter ?")
                .setPositiveButton("Se déconnecter"){ _, _ ->
                    Intent(context, AuthenticationActivity::class.java).also{
                        LogOutUseCase().invoke()
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