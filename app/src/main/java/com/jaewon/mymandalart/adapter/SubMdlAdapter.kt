package com.jaewon.mymandalart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import com.jaewon.mymandalart.MyTouchEvent
import com.jaewon.mymandalart.R
import com.jaewon.mymandalart.data.OneSectionData
import com.jaewon.mymandalart.databinding.OneSectionBinding
import kotlin.math.abs
import kotlin.properties.Delegates

class SubMdlAdapter : RecyclerView.Adapter<SubMdlAdapter.SubMdlViewHolder>() {
    var osList = mutableListOf<OneSectionData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMdlViewHolder {
        return SubMdlViewHolder(OneSectionBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return osList.size
    }

    override fun onBindViewHolder(holder: SubMdlViewHolder, position: Int) {
        holder.bind(osList[position])
    }

    class SubMdlViewHolder(private val binding: OneSectionBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceAsColor", "ClickableViewAccessibility")
        fun bind(osData: OneSectionData) {
            osData.osbind(binding.txtOneSection)
            binding.txtOneSection.movementMethod = null;
//            Log.d("GridTestSMA", "SMA bind ${osData.osNum} of ${osData.m9Num}")

            if (!osData.isCenter){
                binding.txtOneSection.setOnTouchListener { v, event ->
                    if (osData.isEditable) return@setOnTouchListener false

                    when(event.action){

                        MotionEvent.ACTION_DOWN -> {
                        }

                        MotionEvent.ACTION_MOVE -> {
                        }

                        MotionEvent.ACTION_CANCEL -> {
                        }

                        MotionEvent.ACTION_UP -> {
//                        if (osData.isCenter) return@setOnTouchListener false
                            Log.d("MotionAct", "eventAction in M9 : ${event.action}")
                            osData.switchCheckingOS()
                            v.setBackgroundResource(osData.osBackground)
                        }
                    }
                    return@setOnTouchListener true
                }
            }

        }

    }
}