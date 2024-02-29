package com.example.studenchat.chat.ui.stateholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.studenchat.R
import com.example.studenchat.chat.data.Message
import com.example.studenchat.conversation.data.Conversation

class ChatAdapter(
    private var messageList: List<Message>,
    private val conversation: Conversation
) : RecyclerView.Adapter<ViewHolder>() {
    private val ITEM_SENDER = 1
    private val ITEM_RECEIVER = 2

    inner class ChatSenderViewHolder(view: View) : ViewHolder(view)
    inner class ChatReceiverViewHolder(view: View) : ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == ITEM_SENDER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chat_sender, parent, false)
            ChatSenderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chat_receiver, parent, false)
            ChatReceiverViewHolder(view)
        }
    }

    override fun getItemCount(): Int = messageList.size

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].author == "Moi") ITEM_SENDER
        else ITEM_RECEIVER
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val message = messageList[position]
            val profilePicture = findViewById<ImageView>(R.id.img_view_avatar_user_chat)
            val text = findViewById<TextView>(R.id.txt_view_message_chat)
            val hour = findViewById<TextView>(R.id.txt_view_hour_chat)

            profilePicture?.apply{setImageResource(R.drawable.ic_avatar)}
            text.text = message.text
            hour.text = message.dateTime
        }
    }

    fun updateList(newMessageList: List<Message>) {
        val diffResult = DiffUtil.calculateDiff(
            ChatDiffCallback(
                messageList,
                newMessageList
            )
        )
        messageList = newMessageList
        diffResult.dispatchUpdatesTo(this)
    }
}