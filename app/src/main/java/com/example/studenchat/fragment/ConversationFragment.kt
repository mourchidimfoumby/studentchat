package com.example.studenchat.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.studenchat.R
import com.example.studenchat.adapter.ConversationAdapter
import com.example.studenchat.adapter.MainViewPagerAdapter
import com.example.studenchat.databinding.FragmentConversationsBinding
import com.example.studenchat.dialog.AddConversationDialogFragment
import com.example.studenchat.viewmodel.ConversationViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ConversationFragment: Fragment(R.layout.fragment_conversations){
    private lateinit var binding : FragmentConversationsBinding
    private lateinit var recyclerViewConversation: RecyclerView
    private lateinit var txtViewEmptyConversation: TextView
    private lateinit var floatingButtonConversation: FloatingActionButton
    private lateinit var conversationAdapter: ConversationAdapter
    private val viewModel: ConversationViewModel by viewModels()
    val TAG = ConversationFragment::class.java.name
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private val tabLabel = listOf("Conversations", "Amis")
    private val mainViewPagerAdapter =
        MainViewPagerAdapter(listOf(ConversationFragment(), FriendsFragment()), requireActivity())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentConversationsBinding.bind(view)
        recyclerViewConversation = binding.rvConversation
        txtViewEmptyConversation = binding.txtViewEmptyConversation
        floatingButtonConversation = binding.floatingButtonConversation
        tabLayout = binding.tabLayoutMain
        viewPager2 = binding.viewPager2Main
        viewPager2.adapter = mainViewPagerAdapter


        /* navigationView.setNavigationItemSelectedListener(this)
         onBackPressedDispatcher.addCallback {
             if(drawerLayout.isDrawerOpen(GravityCompat.START))
                 drawerLayout.closeDrawer(GravityCompat.START)
         }*/
        TabLayoutMediator(tabLayout, viewPager2){ tab, position ->
            tab.text = tabLabel[position]
        }.attach()
        viewPager2.setCurrentItem(0, false)

        viewModel.conversation.observe(viewLifecycleOwner){conversations ->
            conversationAdapter = ConversationAdapter(conversations){ conversation ->
                Toast.makeText(requireContext(), "Click", Toast.LENGTH_SHORT).show()
            }
            recyclerViewConversation.adapter = conversationAdapter
            recyclerViewConversation.layoutManager = LinearLayoutManager(context)
            if(conversationAdapter.itemCount == 0){
                txtViewEmptyConversation.isVisible = true
                recyclerViewConversation.isVisible = false
            }
        }

        floatingButtonConversation.setOnClickListener {
            AddConversationDialogFragment().show(parentFragmentManager, null)
        }
    }
}