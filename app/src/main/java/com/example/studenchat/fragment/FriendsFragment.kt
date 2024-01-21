package com.example.studenchat.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.adapter.FriendsAdapter
import com.example.studenchat.databinding.FragmentFriendsBinding
import com.example.studenchat.viewmodel.FriendsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FriendsFragment: Fragment(R.layout.fragment_friends) {
    private lateinit var binding : FragmentFriendsBinding
    private lateinit var recyclerViewFriends: RecyclerView
    private lateinit var txtViewEmptyFriends: TextView
    private lateinit var floatingButtonFriends: FloatingActionButton
    private lateinit var friendsAdapter: FriendsAdapter
    private val friendsViewModel: FriendsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFriendsBinding.bind(view)
        recyclerViewFriends = binding.rvFriends
        txtViewEmptyFriends = binding.txtViewEmptyFriends
        floatingButtonFriends = binding.floatingButtonFriends

        friendsViewModel.friends.observe(viewLifecycleOwner){friends ->
            friendsAdapter = FriendsAdapter(friends){
                Toast.makeText(requireContext(), "Click", Toast.LENGTH_SHORT).show()
            }
            recyclerViewFriends.adapter = friendsAdapter
            recyclerViewFriends.layoutManager = LinearLayoutManager(context)
            if(friendsAdapter.itemCount == 0){
                txtViewEmptyFriends.isVisible = true
                recyclerViewFriends.isVisible = false
            }
        }

    }
}