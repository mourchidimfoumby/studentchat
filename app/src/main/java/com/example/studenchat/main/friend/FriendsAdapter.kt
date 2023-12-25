package com.example.studenchat.main.friend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.data.User

class FriendsAdapter(val friendsList: List<User>):
RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>(){
    inner class FriendsViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return FriendsViewHolder(view)
    }

    override fun getItemCount() = friendsList.size

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.itemView.apply {
            val friends = friendsList[position]
            val profilePicture = findViewById<ImageView>(R.id.img_view_avatar_user)
            val friendsName = findViewById<TextView>(R.id.txt_view_user_name)

            profilePicture.setImageResource(friends.picture)
            friendsName.text = String.format("%s %s", friends.firstname, friends.name)
        }
    }


}