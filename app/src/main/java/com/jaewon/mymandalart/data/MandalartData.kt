package com.jaewon.mymandalart.data

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.jaewon.mymandalart.R

data class MandalartData(
    var m9List: MutableList<Matrix9Data> = mutableListOf(),
    var osCenterOfSubList: MutableList<EditText> = mutableListOf(),
    var osSubOfCenterList: MutableList<EditText> = mutableListOf(),
    val ND: NumberData = NumberData()
) {
    fun initMan() {
        with(m9List) {
            for (i in ND.MANSTART..ND.MANEND) {
                val newOSList = mutableListOf<OneSectionData>()
                with(newOSList) {
                    for (j in ND.MANSTART..ND.MANEND) {
                        var center = false
                        if (j==ND.MANCENTER) center = true
                        add(
                            OneSectionData(
                            "1",
                            false,
                                isCenter = center,
                                m9Num = i,
                            osNum = j
                            )
                        )
                    }
                }
                add(Matrix9Data(newOSList, m9Num = i))
            }
        }
    }
    fun setBlue(cnt : Int){
        osSubOfCenterList[cnt].setBackgroundResource(R.drawable.bg_one_section_blue)
    }

    fun wholeCenterUpdate(){
        for (i in 0 until osCenterOfSubList.size){
//            if (i==4) continue
            eachCenterUpdate(osCenterOfSubList[i],osSubOfCenterList[i])
//            Log.d("EDITUP", "subCenterET: ${m9List[i].osList[4].editText}")
        }
    }


    fun eachCenterUpdate(subCenterET: EditText, centerSubET: EditText) {
        var scTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                centerSubET.text = s
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        }
        var csTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                subCenterET.removeTextChangedListener(scTextWatcher)
                subCenterET.text = s
                subCenterET.addTextChangedListener(scTextWatcher)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        }
        subCenterET.addTextChangedListener(scTextWatcher)
        centerSubET.addTextChangedListener(csTextWatcher)

    }
}