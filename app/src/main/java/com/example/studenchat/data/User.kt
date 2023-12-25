package com.example.studenchat.data

import com.example.studenchat.R

data class User(
    val name: String,
    val firstname: String,
    val mail: String,
    val password: String,
    val birthday: String,
    val gender: String,
    val status: String
){
    val picture = if(gender == Gender.HOMME) R.drawable.ic_avatar_student_h
    else R.drawable.ic_avatar_student_f
}
object Gender {
    const val HOMME = "Homme"
    const val FEMME = "Femme"
}

object Status {
    const val ETUDIANT = "Etudiant"
    const val ENSEIGNANT = "Enseignant"
}