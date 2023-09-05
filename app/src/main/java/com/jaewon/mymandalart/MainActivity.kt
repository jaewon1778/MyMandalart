package com.jaewon.mymandalart

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaewon.mymandalart.adapter.MainMdlAdapter
import com.jaewon.mymandalart.data.MandalartData
import com.jaewon.mymandalart.data.Matrix9Data
import com.jaewon.mymandalart.data.OneSectionData
import com.jaewon.mymandalart.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class MainActivity : MyAppActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainMdlAdapter: MainMdlAdapter
    private lateinit var gridManager: GridLayoutManager

    private lateinit var mandalartData : MandalartData
    private var isEditable = false
    private lateinit var dummyEditText:EditText


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dummyEditText = EditText(this)
        mandalartData = MandalartData()
        mandalartData.initMan()
        mainMdlAdapter = MainMdlAdapter()


        gridManager = GridLayoutManager(this, 3)
        setMan()


        binding.btnEdit.setOnClickListener {
//            Log.d("GridTest", "call btnEdit")
            isEditable = !isEditable
            for (mat in mandalartData.m9List){
                for (os in mat.osList){
                    os.isEditable = isEditable
                }
            }
//            Log.d("GridTest", "before updateMan")
            updateMan()
//            Log.d("GridTest", "after updateMan")

            if (isEditable){
                binding.btnEdit.setImageResource(R.drawable.baseline_done_24)

            }
            else {
                saveMan()
                binding.btnEdit.setImageResource(R.drawable.baseline_edit_24)
                softKeyboardHide(this)
            }
            CoroutineScope(Dispatchers.Main).launch {
//                delay(1000)
//                Log.d("GridTest", "before wCUpdate")
                wholeCenterUpdate(binding.mainGrid)
            }

//            Log.d("GridTest", "done btnEdit ============================")
        }
        binding.btnCheck.setOnClickListener {
//            val m9 = binding.mainGrid[ND.MANCENTER] as RecyclerView
//            setBlue(m9[cnt] as EditText)
//            cnt++
        }

        binding.btnTest.setOnClickListener {
        Log.d("GridTest", "call btnTest")
        wholeCenterUpdate(binding.mainGrid)
        Log.d("GridTest", "done btnTest ===============================")
        }

    }

    private fun setMan(){
        binding.mainGrid.apply {
            mainMdlAdapter.m9List = mandalartData.m9List
            layoutManager = gridManager
            adapter = mainMdlAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateMan(){
        Log.d("GridTest", "call updateMan")
        binding.mainGrid.adapter?.notifyDataSetChanged()
        binding.mainGrid.adapter?.notifyItemRangeChanged(0, 8)
        Log.d("GridTest", "done updateMan")
    }

    private fun saveMan(){
        Log.d("GridTest", "call saveMan")
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
        Log.d("GridTest", "done saveMan")
    }


    fun wholeCenterUpdate(maingrid: RecyclerView){

        Log.d("GridTest", "call wCUpdate")
        val cenM9 = maingrid[ND.MANCENTER] as RecyclerView
        for (i in ND.MANSTART .. ND.MANEND){
            if (i == ND.MANCENTER) continue
            val subM9 = maingrid[i] as RecyclerView
            eachCenterUpdate(subM9[ND.MANCENTER] as EditText, cenM9[i] as EditText)
        }
        Log.d("GridTest", "done wCUpdate")
    }


    fun eachCenterUpdate(subCenterET: EditText, centerSubET: EditText) {
        Log.d("GridTest", "call eCUpdate")
        val scTextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                centerSubET.text = s
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        }
        val csTextWatcher = object : TextWatcher {
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
        Log.d("GridTest", "done eCUpdate")
    }

    fun setBlue(target: EditText){
        target.setBackgroundResource(R.drawable.bg_one_section_blue)
    }

}