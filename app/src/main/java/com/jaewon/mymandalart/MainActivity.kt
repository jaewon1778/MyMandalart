package com.jaewon.mymandalart

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.core.view.size
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaewon.mymandalart.adapter.MainMdlAdapter
import com.jaewon.mymandalart.data.MandalartData
import com.jaewon.mymandalart.data.Matrix9Data
import com.jaewon.mymandalart.data.OneSectionData
import com.jaewon.mymandalart.databinding.ActivityMainBinding

class MainActivity : MyAppActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainMdlAdapter: MainMdlAdapter
    private lateinit var gridManager: GridLayoutManager

    private val manData = mutableListOf<Matrix9Data>()
    private lateinit var mandalartData : MandalartData
    private var isEditable = false
    private lateinit var dummyEditText:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dummyEditText = EditText(this)
//        initMan()
        mandalartData = MandalartData(manData)
        mandalartData.initMan()

        mainMdlAdapter = MainMdlAdapter()
        mainMdlAdapter.mandalartData = mandalartData


        gridManager = GridLayoutManager(this, 3)
        initMan()


        var cnt = 0
        binding.btnEdit.setOnClickListener {
            isEditable = !isEditable
            for (mat in mandalartData.m9List){
                for (os in mat.osList){
                    os.isEditable = isEditable
                }
            }
            updateMan()

            if (isEditable){
                binding.btnEdit.setImageResource(R.drawable.baseline_done_24)

            }
            else {
                saveMan()
                binding.btnEdit.setImageResource(R.drawable.baseline_edit_24)
                softKeyboardHide(this)
            }
            cnt = 0
            mandalartData.wholeCenterUpdate()

        }
        binding.btnCheck.setOnClickListener {
            mandalartData.setBlue(cnt)
            cnt++
        }

        binding.btnTest.setOnClickListener {
            mandalartData.wholeCenterUpdate()

        }

    }

    private fun initMan(){
        binding.mainGrid.apply {
            mainMdlAdapter.m9List = mandalartData.m9List
            layoutManager = gridManager
            adapter = mainMdlAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMan(){
        mandalartData.osSubOfCenterList.clear()
        mandalartData.osCenterOfSubList.clear()
        binding.mainGrid.adapter?.notifyDataSetChanged()
        binding.mainGrid.adapter?.notifyItemRangeChanged(0,8)
    }

    private fun saveMan(){
        var rcyM9 : RecyclerView
        var edOS : EditText
        var mat9 : Matrix9Data
        var os : OneSectionData
        for (i in ND.MANSTART..ND.MANEND){
            rcyM9 = binding.mainGrid[i] as RecyclerView
            mat9 = mandalartData.m9List[i]
            for (j in ND.MANSTART..ND.MANEND){
                edOS = rcyM9[j] as EditText
                os = mat9.osList[j]
                if (os.text != edOS.text.toString()){
                    os.text = edOS.text.toString()
                }
            }
        }
    }


}