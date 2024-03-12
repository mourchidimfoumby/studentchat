package com.example.studentchat.chat.ui.stateholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.studentchat.R
import com.example.studentchat.chat.data.Message
import com.example.studentchat.chat.domain.FormatTimestampUseCase
import com.example.studentchat.chat.domain.TimestampToLocalDate
import com.example.studentchat.conversation.data.Conversation
import com.example.studentchat.utils.UNIT
import org.koin.java.KoinJavaComponent.inject
import java.time.Duration
import java.time.Instant

class ChatAdapter(
    private var messageList: List<Message>,
    private val conversation: Conversation,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<ViewHolder>() {

    private val ITEM_SENDER = 1
    private val ITEM_RECEIVER = 2
    private val timestampToLocalDate:
            TimestampToLocalDate by inject(TimestampToLocalDate::class.java)
    private val formatTimestampUseCase:
            FormatTimestampUseCase by inject(FormatTimestampUseCase::class.java)
    inner class ChatSenderViewHolder(itemView: View) : ViewHolder(itemView)
    inner class ChatReceiverViewHolder(itemView: View) : ViewHolder(itemView)

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
        return if (
            messageList[position].author ==
            conversation.otherUser().toString()
            ) ITEM_RECEIVER
        else ITEM_SENDER
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val currentMessage = messageList[position]
            val previousMessage = messageList.elementAtOrNull(position - 1)
            val picture = findViewById<ImageView>(R.id.img_view_avatar_user_chat)
            val text = findViewById<TextView>(R.id.txt_view_message_chat)
            val hour = findViewById<TextView>(R.id.txt_view_hour_chat)

            previousMessage?.let { msg ->
                if(isSentAtSameDate(previousMessage, currentMessage))
                {
                    if (isSentAtSameTime(msg, currentMessage)) {
                        formatSameTimeMessage()
                        if (holder.itemViewType == ITEM_RECEIVER) {
                            formatSameTimeMessageReceive(position - 1)
                        }
                    }
                }
                else{
                    val layout = findViewById<ConstraintLayout>(R.id.constraint_layout)
                    val txtViewDividerChat = layout.findViewById<TextView>(R.id.txt_view_divider_day_chat)
                    txtViewDividerChat.text = formatTimestampUseCase(currentMessage.datetime, UNIT.DAY_MONTH_YEAR)
                    txtViewDividerChat.isVisible = true
                }
            }?: run {
                val layout = findViewById<ConstraintLayout>(R.id.constraint_layout)
                val txtViewDividerChat = layout.findViewById<TextView>(R.id.txt_view_divider_day_chat)
                txtViewDividerChat.text = formatTimestampUseCase(currentMessage.datetime, UNIT.DAY_MONTH_YEAR)
                txtViewDividerChat.isVisible = true
            }

            picture?.setImageResource(R.drawable.ic_avatar)
            text.text = currentMessage.text
            hour.text = formatTimestampUseCase(currentMessage.datetime, UNIT.HOUR_MINUTE)
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

    private fun View.formatSameTimeMessageReceive(position: Int) {
        val previousItem =
            recyclerView.findViewHolderForLayoutPosition(position) as? ChatAdapter.ChatReceiverViewHolder
        previousItem?.itemView?.let {
            val marginStartInDp = 40
            val marginStartInPx =
                (marginStartInDp * resources.displayMetrics.density).toInt()
            val previousLayout = it.findViewById<ConstraintLayout>(R.id.constraint_layout)
            val previousParams = previousLayout.layoutParams as ViewGroup.MarginLayoutParams

            previousParams.marginStart = marginStartInPx
            it.layoutParams = previousParams

            val previousPictureMessage =
                it.findViewById<ImageView>(R.id.img_view_avatar_user_chat)
            previousPictureMessage.isVisible = false
        }
    }

    private fun View.formatSameTimeMessage(){
        val marginTopInDp = 4
        val marginTopInPx =
            (marginTopInDp * resources.displayMetrics.density).toInt()
        val layout = findViewById<ConstraintLayout>(R.id.constraint_layout)
        val params = layout.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = marginTopInPx
        layout.layoutParams = params
    }
    private fun isSentAtSameTime(previousMessage: Message, currentMessage: Message): Boolean {
        val previousMessageInstant = Instant.ofEpochSecond(previousMessage.datetime)
        val currentMessageInstant = Instant.ofEpochSecond(currentMessage.datetime)
        val duration = Duration.between(currentMessageInstant, previousMessageInstant).abs()
        return duration.toMinutes() <= 1
    }

    private fun isSentAtSameDate(previousMessage: Message, currentMessage: Message): Boolean {
        val previousMessageDate = timestampToLocalDate(previousMessage.datetime)
        val currentMessageDate = timestampToLocalDate(currentMessage.datetime)
        return currentMessageDate.isEqual(previousMessageDate)
    }
}
