package com.example.studentchat.friends.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentchat.R
import com.example.studentchat.databinding.FragmentFriendsBinding
import com.example.studentchat.friends.ui.stateholder.FriendsAdapter
import com.example.studentchat.friends.ui.stateholder.FriendsViewModel
import com.example.ui.clickToast
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FriendsFragment: Fragment(R.layout.fragment_friends) {
    private lateinit var binding : FragmentFriendsBinding
    private lateinit var recyclerViewFriends: RecyclerView
    private lateinit var txtViewEmptyFriends: TextView
    private lateinit var floatingButtonFriends: FloatingActionButton
    private lateinit var progressBar: ProgressBar
    private lateinit var friendsAdapter: FriendsAdapter
    private val friendsViewModel: FriendsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFriendsBinding.bind(view)
        recyclerViewFriends = binding.rvFriends
        txtViewEmptyFriends = binding.txtViewEmptyFriends
        floatingButtonFriends = binding.floatingButtonFriends
        progressBar = binding.progressBarFriends

        progressBar.isVisible = true
        friendsAdapter = FriendsAdapter(emptyList()) {friends ->
            requireContext().clickToast(friends.toString())
        }
        recyclerViewFriends.adapter = friendsAdapter
        recyclerViewFriends.layoutManager = LinearLayoutManager(context)

        friendsViewModel.friends.observe(viewLifecycleOwner){ friendsList ->
            friendsAdapter.updateFriendsList(friendsList)
            updateRecyclerViewFriends(friendsAdapter)
        }
        progressBar.isVisible = false
        updateRecyclerViewFriends(friendsAdapter)
//        floatingButtonFriends.setOnClickListener {
//            Intent(requireContext(), AddFriendsActivity::class.java).also {
//                startActivity(it)
//            }
//        }
    }

    private fun updateRecyclerViewFriends(friendsAdapter: FriendsAdapter){
        if (friendsAdapter.itemCount == 0) {
            txtViewEmptyFriends.isVisible = true
            recyclerViewFriends.isVisible = false
        }
        else{
            txtViewEmptyFriends.isVisible = false
            recyclerViewFriends.isVisible = true
        }
    }
}