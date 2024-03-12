package com.example.studentchat.chat.ui.stateholder

import androidx.recyclerview.widget.DiffUtil
import com.example.studentchat.chat.data.Message

class ChatDiffCallback(
    private val oldList: List<Message>,
    private val newList: List<Message>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMessage = oldList[oldItemPosition]
        val newMessage = newList[newItemPosition]
        return oldMessage == newMessage
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].text == newList[newItemPosition].text
    }

}