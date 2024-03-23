package com.example.studentchat.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.studentchat.authentication.domain.LogOutUseCase
import org.koin.java.KoinJavaComponent.inject

class DisconnectionDialogFragment: DialogFragment() {
    private val logOutUseCase: LogOutUseCase by inject(LogOutUseCase::class.java)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("Voulez-vous vraiment vous déconnecter ?")
                .setPositiveButton("Se déconnecter"){ _, _ ->
                    logOutUseCase()
                }
                .setNegativeButton("Annuler"){ dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("L'activité ne peut pas etre nulle")
    }
}