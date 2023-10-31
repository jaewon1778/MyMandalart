package com.jaewon.mymandalart

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 툴바 세팅
        val toolbar = binding.myToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = "툴툴바바"
        // 툴바 세팅

        dummyEditText = EditText(this)
        mandalartData = MandalartData()
        mandalartData.initMan()
        mainMdlAdapter = MainMdlAdapter()


        gridManager = GridLayoutManager(this, 3)
        setMan()


        binding.btnEdit.setOnClickListener {
            isEditable = !isEditable
            for (mat in mandalartData.m9List){
                for (os in mat.osList){
                    os.isEditable = isEditable
                    Log.d("GridTestChange", "change ${os.osNum} of ${os.m9Num}")
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
            CoroutineScope(Dispatchers.Main).launch {
//                delay(1000)
                wholeCenterUpdate(binding.mainGrid)
            }

        }
        binding.btnCheck.setOnClickListener {
//            val m9 = binding.mainGrid[ND.MANCENTER] as RecyclerView
//            setBlue(m9[cnt] as EditText)
//            cnt++
        }

        binding.btnTest.setOnClickListener {
        wholeCenterUpdate(binding.mainGrid)
        }

    }

    private fun setMan(){

        binding.mainGrid.apply {
            mainMdlAdapter.m9List = mandalartData.m9List
            layoutManager = gridManager
            adapter = mainMdlAdapter
        }

//        binding.mainGrid.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener{
//            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
//                if (e.action == MotionEvent.ACTION_DOWN){
////                        m9Data.osList[4].checkOS()
//                    val child: View = rv.findChildViewUnder(e.x,e.y) ?: return false
//                    val position = rv.getChildLayoutPosition(child)
////                        if(m9Data.osList[position].isEditable) return false
//                    Log.d("GridTestTouchMain", "position : $position")
//                    return false
//                }
//                return false
//            }
//
//            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
//            }
//
//            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//            }
//
//        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateMan(){
        binding.mainGrid.adapter?.notifyDataSetChanged()
//        binding.mainGrid.adapter?.notifyItemRangeChanged(ND.MANSTART, ND.MANEND+1)
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


    fun wholeCenterUpdate(maingrid: RecyclerView){

        val cenM9 = maingrid[ND.MANCENTER] as RecyclerView
        for (i in ND.MANSTART .. ND.MANEND){
            if (i == ND.MANCENTER) continue
            val subM9 = maingrid[i] as RecyclerView
            eachCenterUpdate(subM9[ND.MANCENTER] as EditText, cenM9[i] as EditText)
        }
    }


    fun eachCenterUpdate(subCenterET: EditText, centerSubET: EditText) {
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
    }

    fun setBlue(target: EditText){
        target.setBackgroundResource(R.drawable.bg_one_section_blue)
    }

}