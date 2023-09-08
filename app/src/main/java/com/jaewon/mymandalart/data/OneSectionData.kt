package com.jaewon.mymandalart.data

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

}
