package com.example.studenchat.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.studenchat.R
import com.example.studenchat.convertDateToString
import com.example.studenchat.model.User
import com.example.studenchat.databinding.ActivityRegistrationBinding
import com.example.studenchat.inputIsNotEmpty
import com.example.studenchat.repository.firebase.FirebaseAuthenticationHelper
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class RegistrationActivity : AppCompatActivity(){
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
                user = User (
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
        try {
            FirebaseAuthenticationHelper.registration(user)
            Intent(context, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }catch(e: Exception) {
            Toast.makeText(
                context,
                "Erreur d'inscription, veuillez réessayer",
                Toast.LENGTH_SHORT
            ).show()
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