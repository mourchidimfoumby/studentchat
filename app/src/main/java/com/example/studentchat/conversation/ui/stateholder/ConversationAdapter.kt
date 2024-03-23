package com.example.studentchat.conversation.ui.stateholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentchat.R
import com.example.studentchat.chat.domain.FormatTimestampUseCase
import com.example.studentchat.conversation.data.Conversation
import com.example.studentchat.utils.UNIT
import org.koin.java.KoinJavaComponent

class ConversationAdapter(
    private var conversationList: List<Conversation>,
    private val clickListener: (Conversation) -> Unit
) :
    RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>() {
    private val formatTimestampUseCase:
            FormatTimestampUseCase by KoinJavaComponent.inject(FormatTimestampUseCase::class.java)
    inner class ConversationViewHolder(view: View, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                clickAtPosition(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_conversation, parent, false)
        return ConversationViewHolder(view) {
            clickListener(conversationList[it])
        }
    }

    override fun getItemCount() = conversationList.size
    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.itemView.apply {
            val conversation = conversationList[position]
            val title = findViewById<TextView>(R.id.txt_view_title_conversation)
            val lastMessage = findViewById<TextView>(R.id.txt_view_message_conversation)
            val hourConversation = findViewById<TextView>(R.id.txt_view_hour_conversation)
            val imgAvatar = findViewById<ImageView>(R.id.img_view_avatar_user_conversation)

            lastMessage.text = conversation.lastMessage.text ?: "Message supprimé"
            hourConversation.text = conversation.lastMessage.timestamp.let {
                formatTimestampUseCase(it, UNIT.HOUR_MINUTE)
            }
            title.text = conversation.otherUser().toString()
            imgAvatar.setImageResource(conversation.picture)
        }
    }

    fun updateConversationList(newConversationList: List<Conversation>) {
        val diffResult = DiffUtil.calculateDiff(
            ConversationDiffCallback(
                conversationList,
                newConversationList
            )
        )
        conversationList = newConversationList
        diffResult.dispatchUpdatesTo(this)
    }

}