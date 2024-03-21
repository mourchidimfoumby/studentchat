package com.example.studentchat.friends.ui.stateholder

//class AddFriendsAdapter(
//    private var userList: List<User>,
//    private val clickLister: (User) -> Unit
//) : RecyclerView.Adapter<AddFriendsAdapter.AddFriendsViewHolder>() {
//    inner class AddFriendsViewHolder(itemView: View, clickAtPosition: (Int) -> Unit): RecyclerView.ViewHolder(itemView){
//        init {
//            itemView.findViewById<Button>(R.id.btn_add_friends).setOnClickListener{
//                clickAtPosition(bindingAdapterPosition)
//            }
//        }
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFriendsViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_add_friends, parent, false)
//        return AddFriendsViewHolder(view){
//            clickLister(userList[it])
//        }
//    }
//
//    override fun getItemCount(): Int = userList.size
//
//    override fun onBindViewHolder(holder: AddFriendsViewHolder, position: Int) {
//        holder.itemView.apply {
//            val user = userList[position]
//            val username = findViewById<TextView>(R.id.txt_view_user_name_add_friends)
//            val profilePicture = findViewById<ImageView>(R.id.img_view_avatar_user_add_friends)
//
//            username.text = user.toString()
//            profilePicture.setImageResource(user.picture)
//        }
//    }
//
//    fun updateUserList(newUserList: List<User>){
//        val diffResult = DiffUtil.calculateDiff(FriendsDiffCallback(userList, newUserList))
//        userList = newUserList
//        diffResult.dispatchUpdatesTo(this)
//    }
//}