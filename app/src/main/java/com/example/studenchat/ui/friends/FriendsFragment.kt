package com.example.studenchat.ui.friends

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenchat.R
import com.example.studenchat.databinding.FragmentFriendsBinding
import com.example.studenchat.utils.ToastUtils.Companion.clickToast
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
            requireContext().clickToast("Click sur ${friends.firstname}")
        }
        recyclerViewFriends.adapter = friendsAdapter
        recyclerViewFriends.layoutManager = LinearLayoutManager(context)

        friendsViewModel.friends.observe(viewLifecycleOwner){ friendsList ->
            progressBar.isVisible = false
            friendsAdapter.updateFriendsList(friendsList)
            if(friendsAdapter.itemCount > 0){
                txtViewEmptyFriends.isVisible = false
                recyclerViewFriends.isVisible = true
            }
            else{
                txtViewEmptyFriends.isVisible = true
                recyclerViewFriends.isVisible = false
            }
        }
//        floatingButtonFriends.setOnClickListener {
//            Intent(requireContext(), AddFriendsActivity::class.java).also {
//                startActivity(it)
//            }
//        }
    }
}