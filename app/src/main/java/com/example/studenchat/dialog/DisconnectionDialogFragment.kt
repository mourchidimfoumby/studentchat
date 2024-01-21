package com.example.studenchat.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.studenchat.activity.AuthenticationActivity
import com.example.studenchat.repository.firebase.FirebaseAuthenticationHelper

class DisconnectionDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("Se déconnecter")
                .setPositiveButton("Oui"){ _, _ ->
                    Intent(context, AuthenticationActivity::class.java).also{
                        FirebaseAuthenticationHelper.signOut()
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