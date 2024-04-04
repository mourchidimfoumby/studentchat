package com.example.authentication.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.authentication.PATTERN_DAY_MONTH_YEAR
import com.example.authentication.R
import com.example.authentication.convertDateToString
import com.example.authentication.databinding.ActivityRegistrationBinding
import com.example.authentication.inputIsEmpty
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var inputMail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var txtViewError: TextView
    private lateinit var btnRgistration: Button
    private lateinit var inputFirstName: TextInputEditText
    private lateinit var inputName: TextInputEditText
    private lateinit var inputBirthday: TextInputEditText
    private lateinit var progressBar: ProgressBar
    private val authenticationViewModel: AuthenticationViewModel by viewModels()
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
            if (inputIsEmpty(inputList)) {
                txtViewError.isVisible = true
                txtViewError.text = getString(R.string.error_input_not_empty)
            }
            else if(!verifPassword()){
                txtViewError.isVisible = true
                txtViewError.text = getString(R.string.error_short_password)
            }
            else{
                registration(inputMail.text.toString(), inputPassword.text.toString())
            }
        }
        inputBirthday.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun registration(mail: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                authenticationViewModel.signUpWithEmailPassword(mail, password)
            } catch (e: Exception) {
                Log.e(javaClass.name, e.cause!!.message.toString())
                Toast.makeText(
                    this@RegistrationActivity,
                    "Erreur d'inscription, veuillez réessayer",
                    Toast.LENGTH_SHORT
                ).show()
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
                inputBirthday.setText(convertDateToString(calendar.time, PATTERN_DAY_MONTH_YEAR))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

}
