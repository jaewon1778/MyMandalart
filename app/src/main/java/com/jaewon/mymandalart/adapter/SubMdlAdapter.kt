package com.jaewon.mymandalart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import com.jaewon.mymandalart.MyTouchEvent
import com.jaewon.mymandalart.R
import com.jaewon.mymandalart.data.OneSectionData
import com.jaewon.mymandalart.databinding.OneSectionBinding

class SubMdlAdapter : RecyclerView.Adapter<SubMdlAdapter.SubMdlViewHolder>() {
    var osList = mutableListOf<OneSectionData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMdlViewHolder {
        return SubMdlViewHolder(OneSectionBinding
            .inflate(LayoutInflater.from(parent.context),parent,false), parent.context)
    }

    override fun getItemCount(): Int {
        return osList.size
    }

    override fun onBindViewHolder(holder: SubMdlViewHolder, position: Int) {
        holder.bind(osList[position])
    }

    class SubMdlViewHolder(private val binding: OneSectionBinding, parentContext: Context) : RecyclerView.ViewHolder(binding.root) {
        private val myTouchEvent: MyTouchEvent = MyTouchEvent(parentContext)
        private val mDetector: GestureDetectorCompat = GestureDetectorCompat(parentContext,myTouchEvent)

        @SuppressLint("SetTextI18n", "ResourceAsColor", "ClickableViewAccessibility")
        fun bind(osData: OneSectionData) {
            binding.txtOneSection.setText(osData.text)
            binding.txtOneSection.isClickable = osData.isEditable
            binding.txtOneSection.isLongClickable = osData.isEditable
            binding.txtOneSection.isFocusable = osData.isEditable
            binding.txtOneSection.isFocusableInTouchMode = osData.isEditable
            binding.txtOneSection.setBackgroundResource(osData.osBackground)


//            mDetector.setOnDoubleTapListener(myTouchEvent)
//            binding.txtOneSection.setOnTouchListener { v, event ->
//                val reaction = mDetector.onTouchEvent(event)
//                val GN : Int = myTouchEvent.getGestureNum()
//                if (osData.isEditable) return@setOnTouchListener reaction
//
//                if (GN == myTouchEvent.FLING_LEFT){
//                    v.setBackgroundResource(R.drawable.bg_one_section_blue)
//                }
//                else if (GN == myTouchEvent.FLING_RIGHT){
//                    v.setBackgroundResource(R.drawable.bg_one_section)
//                }
//                return@setOnTouchListener reaction
//            }
            Log.d("GridTestSMA", "SMA bind ${osData.osNum} of ${osData.m9Num}")
        }

    }
}