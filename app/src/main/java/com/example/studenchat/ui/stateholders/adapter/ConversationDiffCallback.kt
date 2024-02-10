package com.example.studenchat.ui.stateholders.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.studenchat.data.sources.Conversation

class ConversationDiffCallback (
    private val oldList: List<Conversation>,
    private val newList: List<Conversation>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
