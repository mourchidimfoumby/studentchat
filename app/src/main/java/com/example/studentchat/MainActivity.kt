package com.example.studentchat
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.studentchat.authentication.AuthenticationActivity
import com.example.studentchat.databinding.ActivityMainBinding
import com.example.studentchat.conversation.ui.ConversationFragment
import com.example.studentchat.dialog.DisconnectionDialogFragment
import com.example.studentchat.friends.ui.FriendsFragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity(){
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var btmNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Firebase.auth.currentUser == null){
            Intent(this, AuthenticationActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btmNavigationView = binding.btmNavigationView
        btmNavigationView.setOnItemSelectedListener{itemMenu ->
            when(itemMenu.itemId){
                R.id.nav_item_conversation -> setCurrentFragment(ConversationFragment())
                R.id.nav_item_friends -> setCurrentFragment(FriendsFragment())
            }
            true
        }
        setCurrentFragment(ConversationFragment())
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_main, fragment)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.app_bar_item_logout -> DisconnectionDialogFragment().show(supportFragmentManager, null)
        }
        return true
    }
}
