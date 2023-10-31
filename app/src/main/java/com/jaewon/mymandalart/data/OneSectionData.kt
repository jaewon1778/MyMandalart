package com.jaewon.mymandalart.data

import android.widget.EditText

data class OneSectionData(
    var text: String = "",
    var checked: Boolean = false,
    var isCenter: Boolean = false,
    var isEditable: Boolean = false,
    var m9Num: Int,
    var osNum: Int,
    var osBackground: Int = OSBackground().basicBG
) {
    val osBG = OSBackground()

    fun osbind(osEditText: EditText){
        osEditText.setText(text)
        osEditText.isClickable = isEditable
        osEditText.isLongClickable = isEditable
        osEditText.isFocusable = isEditable
        osEditText.isFocusableInTouchMode = isEditable
        osEditText.setBackgroundResource(osBackground)
    }

    fun checkOS(){
        checked = true
        osBackground = osBG.checkedBG
    }
    fun uncheckOS(){
        checked = false
        osBackground = osBG.basicBG
    }
    fun switchCheckingOS(){
        if (checked) uncheckOS()
        else checkOS()
    }

    fun centerCheckingOS():Boolean{
        return true
    }

    fun setBGYellow(){
        osBackground = osBG.yBG
    }
    fun setBGRed(){
        osBackground = osBG.rBG
    }
    fun setBGBlue(){
        osBackground = osBG.checkedBG
    }
    fun setBGGreen(){
        osBackground = osBG.gBG
    }

}
