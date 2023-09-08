package com.jaewon.mymandalart.data

data class Matrix9Data(
    var osList : MutableList<OneSectionData> = mutableListOf(),
    var isCenter : Boolean = false,
    var m9Num : Int
){
    val ND = NumberData()
    // 텍스트 초기화
    fun clearSubTextM9(){
        for (osData in osList){
            if (osData.isCenter) continue
            osData.text = ""
        }
    }
    fun clearCenTextM9(){
        osList[ND.MANCENTER].text = ""
    }
    fun clearAllTextM9(){
        for (osData in osList){
            osData.text = ""
        }
    }

    // 체크 확인
    // 센터?
    // os의 m9Num 변경
    // osNum 변경

}
