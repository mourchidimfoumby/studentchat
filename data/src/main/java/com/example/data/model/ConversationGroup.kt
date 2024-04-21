package com.example.data.model

import com.example.data.R

data class ConversationGroup(
    val id: String,
    val listInterlocutors: MutableList<User>,
    var messages: List<Message>,
    var picture: Int = R.drawable.ic_group
) {
    var title: String = defaultTitle()
    private fun defaultTitle(): String {
        val fullTitle = ""
        listInterlocutors.forEach {
            fullTitle.plus("$it, ")
        }
        fullTitle.dropLast(2)
        return fullTitle
    }
}