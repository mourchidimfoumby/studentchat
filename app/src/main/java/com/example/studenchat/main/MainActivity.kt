package com.example.studenchat.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.studenchat.R
import com.example.studenchat.databinding.ActivityMainBinding
import com.example.studenchat.main.app.FragmentApps
import com.example.studenchat.main.conversation.FragmentConversation
import com.example.studenchat.main.friend.FragmentFriends
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var btmNavigationMain : BottomNavigationView
    private val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentConversation = FragmentConversation(context)
        val fragmentFriends = FragmentFriends()
        val fragmentApps = FragmentApps()
        setCurrentFragment(fragmentConversation)

        btmNavigationMain = binding.btmNavigationMain
        btmNavigationMain.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_item_friends -> {
                    setCurrentFragment(fragmentFriends)
                }
                R.id.menu_item_conversation -> {
                    setCurrentFragment(fragmentConversation)
                }
                R.id.menu_item_apps -> {
                    setCurrentFragment(fragmentApps)
                }
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_main, fragment)
            commitNow()
        }

}