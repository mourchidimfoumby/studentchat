package com.example.studenchat.model

import com.example.studenchat.R
import com.example.studenchat.model.Message
import com.example.studenchat.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.Exclude
import com.google.firebase.ktx.Firebase

data class Conversation(
    val interlocutors: List<User> = listOf(),
){
    var messages: List<Message>? = null
    var id: String? = null
    var title: String? = null
    var picture: Int? = null

    init {
        require(interlocutors.size >= 2){"La conversation doit avoir au minimum deux utilisateur"}
    }
    @Exclude
    fun goodTitlePicture(): Pair<String, Int>{
        return if(interlocutors.size > 2){
            val fullTitle = ""
            interlocutors.forEach {
                fullTitle.plus("$it, ")
            }
            fullTitle.dropLast(2)
            Pair(fullTitle, R.drawable.ic_group)
        }
        else{
            val otherUser = otherUser()
            Pair(otherUser.toString(), otherUser.picture)
        }
    }
    @Exclude
    private fun otherUser(): User{
        return if(interlocutors[0].uid == Firebase.auth.uid) interlocutors[1]
        else interlocutors[0]
    }
}