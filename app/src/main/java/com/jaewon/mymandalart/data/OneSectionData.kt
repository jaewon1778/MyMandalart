package com.jaewon.mymandalart.data

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.jaewon.mymandalart.R
import kotlin.coroutines.coroutineContext

data class OneSectionData(
    var text : String = "",
    var checked : Boolean = false,
    var isCenter : Boolean = false,
    var isEditable : Boolean = false,
    var m9Num : Int,
    var osNum : Int,
//    var editText: EditText
//    var mandalartData: MandalartData = MandalartData()
//    var color: Int = R.color.blue
) {
    private val textWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {

        }
    }
}

