package com.jaewon.mymandalart.data

import android.graphics.Color
import com.jaewon.mymandalart.R

data class Matrix9Data(
    var osList : MutableList<OneSectionData> = mutableListOf(),
    var isCenter : Boolean = false,
    var m9Num : Int
)
