package com.example.studenchat.authentication.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.DatePicker
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.studenchat.R
import com.example.studenchat.authentication.domain.SignUpUseCase
import com.example.studenchat.data.sources.User
import com.example.studenchat.databinding.ActivityRegistrationBinding
import com.example.studenchat.ui.elements.activity.MainActivity
import com.example.studenchat.utils.DateUtils.Companion.convertDateToString
import com.example.studenchat.utils.InputUtils.Companion.inputIsNotEmpty
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var inputMail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var txtViewError: TextView
    private lateinit var btnRgistration: Button
    private lateinit var user: User
    private lateinit var inputFirstName: TextInputEditText
    private lateinit var inputName: TextInputEditText
    private lateinit var inputBirthday: TextInputEditText
    private val context: Context = this
    private val calendar = Calendar.getInstance()
    private lateinit var progressBar: ProgressBar
    private val signUp = SignUpUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnRgistration = binding.buttonRegistration
        txtViewError = binding.txtViewRegisterError
        inputMail = binding.inputTextMailRegistration
        inputPassword = binding.inputTextPasswordRegistration
        inputName = binding.inputTextNameRegistration
        inputFirstName = binding.inputTextFirstnameRegistration
        inputBirthday = binding.inputTextBirthdayRegistration
        progressBar = binding.progressBarRegistration

        btnRgistration.setOnClickListener {
            txtViewError.isVisible = false
            val inputList = listOf(
                inputName,
                inputFirstName,
                inputMail,
                inputPassword,
                inputBirthday,
            )
            if (inputIsNotEmpty(inputList) && verifPassword()) {
                user = User(
                    "",
                    inputName.text.toString(),
                    inputFirstName.text.toString(),
                    inputMail.text.toString(),
                    inputPassword.text.toString(),
                    inputBirthday.text.toString(),

                    )
                registration()
            } else {
                txtViewError.isVisible = true
            }
        }
        inputBirthday.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun registration() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                signUp(user)
                withContext(Dispatchers.Main) {
                    Intent(context, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e(javaClass.name, e.message.toString())
                    Toast.makeText(
                        context,
                        "Erreur d'inscription, veuillez réessayer",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun verifPassword(): Boolean {
        return if (inputPassword.text.toString().length < 6) {
            txtViewError.text = getString(R.string.error_short_password)
            false
        } else {
            true
        }
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(year, month, dayOfMonth)
                inputBirthday.setText(convertDateToString(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

}