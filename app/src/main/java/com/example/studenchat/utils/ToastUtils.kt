package com.example.studenchat.utils

import android.content.Context
import android.widget.Toast

class ToastUtils {
    companion object{
        fun Context.clickToast(element: String){
            Toast.makeText(this,"Click sur $element", Toast.LENGTH_SHORT).show()
        }
    }
}