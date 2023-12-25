package com.example.studenchat.authentication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.studenchat.main.MainActivity
import com.example.studenchat.R
import com.example.studenchat.data.User
import com.example.studenchat.databinding.ActivityRegistrationBinding
import com.example.studenchat.firebase.FirebaseRegistrationHelper
import com.example.studenchat.inputIsNotEmpty
import com.google.android.material.textfield.TextInputEditText

class RegistrationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var inputMail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var txtViewError: TextView
    private lateinit var btnRgistration: Button
    private lateinit var user: User
    private lateinit var inputFirstName: TextInputEditText
    private lateinit var inputName: TextInputEditText
    private lateinit var inputBirthday: TextInputEditText
    private lateinit var spinnerStatus: Spinner
    private lateinit var spinnerGender: Spinner
    private lateinit var gender: String
    private lateinit var status: String
    private val context: Context = this

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
        spinnerGender = binding.spinnerGenderRegistration
        spinnerStatus = binding.spinnerStatusRegistration
        spinnerGender.onItemSelectedListener = this
        spinnerStatus.onItemSelectedListener = this

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
                    inputName.text.toString(),
                    inputFirstName.text.toString(),
                    inputMail.text.toString(),
                    inputPassword.text.toString(),
                    inputBirthday.text.toString(),
                    gender,
                    status
                )
                registration()
            } else {
                txtViewError.isVisible = true
            }
        }
    }

    private fun registration() {
        try {
            FirebaseRegistrationHelper.registerUserWithEmailAndPassword(user)
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        gender = parent!!.getItemAtPosition(position).toString()
        status = parent.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}