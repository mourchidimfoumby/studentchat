package com.example.studenchat.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout.LayoutParams
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.adapter.FriendsAdapter
import com.example.studenchat.model.Conversation
import com.example.studenchat.model.User
import com.example.studenchat.viewmodel.ConversationViewModel
import com.example.studenchat.viewmodel.FriendsViewModel

class AddConversationDialogFragment : DialogFragment(){
    private val conversationViewModel : ConversationViewModel by viewModels()
    private val friendsViewModel : FriendsViewModel by viewModels()
    private val listClicked = mutableListOf<User>()
    private lateinit var recyclerViewFriendsConversation: RecyclerView
    private lateinit var btnCreate: Button
    private lateinit var btnCancel: Button
    private lateinit var friendsAdapter: FriendsAdapter
    private val TAG = AddConversationDialogFragment::class.java.name

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_conversation_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewFriendsConversation = view.findViewById(R.id.rv_friends_conversation)
        friendsAdapter = FriendsAdapter(emptyList()) { friend ->
            listClicked.add(friend)
            Log.d(TAG, "Friends: $friend")
        }
        recyclerViewFriendsConversation.adapter = friendsAdapter
        recyclerViewFriendsConversation.layoutManager = LinearLayoutManager(view.context)
        friendsViewModel.friends.observe(viewLifecycleOwner) { friends ->
            friendsAdapter.updateFriendsList(friends)
        }
        btnCreate = view.findViewById(R.id.btn_add_conversation_create)
        btnCancel = view.findViewById(R.id.btn_add_conversation_cancel)
        btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        val params = dialog!!.window!!.attributes
        params.width = LayoutParams.MATCH_PARENT
        params.height = LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params
    }
}