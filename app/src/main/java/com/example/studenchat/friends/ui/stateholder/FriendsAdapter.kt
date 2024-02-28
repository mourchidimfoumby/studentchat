package com.example.studenchat.friends.ui.stateholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.user.data.User

class FriendsAdapter(private var friendsList: List<User>, private val clickListener: (User) -> Unit):
    RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>(){
    inner class FriendsViewHolder(view: View, clickAtPosition: (Int) -> Unit): RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                clickAtPosition(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return FriendsViewHolder(view){
            clickListener(friendsList[it])
        }
    }

    override fun getItemCount() = friendsList.size

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.itemView.apply {
            val friend = friendsList[position]
            val profilePicture = findViewById<ImageView>(R.id.img_view_avatar_user)
            val friendsName = findViewById<TextView>(R.id.txt_view_user_name)

            profilePicture.setImageResource(friend.picture)
            friendsName.text = friend.toString()
        }
    }

    fun updateFriendsList(newFriendsList: List<User>){
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(friendsList, newFriendsList))
        friendsList = newFriendsList
        diffResult.dispatchUpdatesTo(this)
    }

}