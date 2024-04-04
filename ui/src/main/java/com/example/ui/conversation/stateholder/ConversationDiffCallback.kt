package com.example.ui.conversation.stateholder

import androidx.recyclerview.widget.DiffUtil
import com.example.data.model.Conversation

class ConversationDiffCallback (
    private val oldList: List<Conversation>,
    private val newList: List<Conversation>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}