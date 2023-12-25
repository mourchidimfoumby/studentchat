package com.example.studenchat

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.studenchat.main.conversation.FragmentConversation
import com.example.studenchat.main.friend.FriendsAdapter
import com.google.android.material.textfield.TextInputEditText

fun inputIsNotEmpty(inputList: List<TextInputEditText>): Boolean {
    inputList.forEach {
        if (it.text!!.isBlank()) {
            return false
        }
    }
    return true
}

fun showPopup(context: Context, container: View, idLayout: Int){
    val layoutInflater = LayoutInflater.from(context)
    val popupView = layoutInflater.inflate(idLayout,null)
    val width = LinearLayout.LayoutParams.WRAP_CONTENT
    val height = LinearLayout.LayoutParams.WRAP_CONTENT
    Log.d("Utils", ""+width + ""+ height)
    val focusable = true
    val recyclerViewFriends = popupView.findViewById<RecyclerView>(R.id.rv_friends_conversation)
    recyclerViewFriends.adapter = FriendsAdapter(FragmentConversation.users)
    recyclerViewFriends.layoutManager = LinearLayoutManager(context)
    val popupWindow = PopupWindow(popupView, width, height, focusable)
    popupWindow.showAtLocation(container, Gravity.CENTER,0,0)
    container.setOnTouchListener { v, event ->
        popupWindow.dismiss()
        return@setOnTouchListener true
    }

}