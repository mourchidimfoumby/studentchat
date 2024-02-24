package com.example.studenchat.data.source

import com.example.studenchat.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.Exclude

data class Conversation(
<<<<<<< Updated upstream
    val interlocutors: Pair<User,User>,
){
    var id: String = ""
    var lastMessage: String = ""
    @Exclude
    var title: String = otherUser().toString()
    @Exclude
    var picture: Int = R.drawable.ic_avatar

    @Exclude
    private fun otherUser(): User {
        return if(interlocutors.first.uid == Firebase.auth.uid) interlocutors.second
        else interlocutors.first
=======
    val interlocutors: Pair<User, User>? = null,
    var id: String = "",
    var lastMessage: Message? = null
) {
    @get:Exclude
    @set:Exclude
    var picture: Int = R.drawable.ic_avatar

    @Exclude
    fun otherUser(): User {
        return if (interlocutors != null) {
            if (interlocutors.first.uid == Firebase.auth.uid) interlocutors.second
            else interlocutors.first
        } else User()
>>>>>>> Stashed changes
    }
}

data class ConversationGroup(
    val listInterlocutors: MutableList<User>
){
    var id: String? = null
    var messages: List<Message>? = null
    var title: String = defaultTitle()
    var picture: Int = R.drawable.ic_group

    @Exclude
    private fun defaultTitle(): String{
        val fullTitle = ""
        listInterlocutors.forEach {
            fullTitle.plus("$it, ")
        }
        fullTitle.dropLast(2)
        return fullTitle
    }
}

data class ConversationDTO(
    val interlocutors: List<Map<String, Boolean>>? = null,
    var id: String = "",
    var lastMessage: Message? = null
)