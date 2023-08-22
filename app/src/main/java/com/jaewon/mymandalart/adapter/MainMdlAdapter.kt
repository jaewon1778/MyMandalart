package com.jaewon.mymandalart.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaewon.mymandalart.data.MandalartData
import com.jaewon.mymandalart.data.Matrix9Data
import com.jaewon.mymandalart.databinding.Matrix9Binding

class MainMdlAdapter : RecyclerView.Adapter<MainMdlAdapter.MainMdlViewHolder>() {
    var m9List = mutableListOf<Matrix9Data>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMdlViewHolder {
        return MainMdlViewHolder(Matrix9Binding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return m9List.size
    }



    override fun onBindViewHolder(holder: MainMdlViewHolder, position: Int) {
        holder.bind(m9List[position])
    }

    class MainMdlViewHolder(private val binding: Matrix9Binding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(m9Data: Matrix9Data) {
            val subMdlAdapter = SubMdlAdapter()

//            subMdlAdapter.m9Num = m9Data.m9Num
            subMdlAdapter.osList = m9Data.osList
            binding.gridMatrix.apply {
                adapter = subMdlAdapter
                layoutManager = GridLayoutManager(binding.gridMatrix.context, 3)
            }

        }


    }
}