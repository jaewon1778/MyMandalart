package com.jaewon.mymandalart.data

data class MandalartData(
    var m9List: MutableList<Matrix9Data> = mutableListOf(),
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
}