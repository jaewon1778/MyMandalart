package com.jaewon.mymandalart.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaewon.mymandalart.data.MandalartData
import com.jaewon.mymandalart.data.NumberData
import com.jaewon.mymandalart.data.OneSectionData
import com.jaewon.mymandalart.databinding.OneSectionBinding

class SubMdlAdapter : RecyclerView.Adapter<SubMdlAdapter.SubMdlViewHolder>() {
    lateinit var mandalartData : MandalartData
//    var m9Num : Int = 0
    var osList = mutableListOf<OneSectionData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMdlViewHolder {
        return SubMdlViewHolder(OneSectionBinding
            .inflate(LayoutInflater.from(parent.context),parent,false),mandalartData)
    }

    override fun getItemCount(): Int {
        return osList.size
    }

    override fun onBindViewHolder(holder: SubMdlViewHolder, position: Int) {
        holder.bind(osList[position])
    }

    class SubMdlViewHolder(private val binding: OneSectionBinding, var mandalartData: MandalartData) : RecyclerView.ViewHolder(binding.root) {
        val ND = NumberData()

        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(osData: OneSectionData) {
            binding.txtOneSection.setText(osData.text)
            binding.txtOneSection.isFocusable = osData.isEditable
            binding.txtOneSection.isFocusableInTouchMode = osData.isEditable
//            osData.editText = binding.txtOneSection
//            Log.d("EDIT", "osData.editText: ${osData.editText}")
            if (osData.isCenter && osData.m9Num != ND.MANCENTER ){
                mandalartData.osCenterOfSubList.add(binding.txtOneSection)
//                Log.d("osos", "add osCS")
            }
            if (!osData.isCenter && osData.m9Num == ND.MANCENTER ){
                mandalartData.osSubOfCenterList.add(binding.txtOneSection)
//                Log.d("osos", "add osSC")
            }

//            binding.txtOneSection.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
//                if (hasFocus){
//
//                }
//                else {
//                    osData.text = binding.txtOneSection.text.toString()
//                }
//            }
        }

    }
}