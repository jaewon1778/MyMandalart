package com.jaewon.mymandalart

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.jaewon.mymandalart.data.NumberData

open class MyAppActivity : AppCompatActivity(){
    val ND = NumberData()

    fun softKeyboardHide(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }

}