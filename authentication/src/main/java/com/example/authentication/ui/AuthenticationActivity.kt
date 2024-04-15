package com.example.authentication.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.authentication.R
import com.example.authentication.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCurrentFragment(ConnectionFragment())
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().also {
            it.replace(R.id.frame_layout_authentication, fragment)
            it.commit()
        }
    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().also {
            it.add(R.id.frame_layout_authentication, fragment)
            it.addToBackStack(null)
            it.commit()
        }
    }

    fun removeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().also {
            it.remove(fragment)
            it.commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                supportFragmentManager.popBackStackImmediate()
                supportActionBar?.hide()
            }
        }
        return true
    }
}

