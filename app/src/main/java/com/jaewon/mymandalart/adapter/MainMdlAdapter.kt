package com.jaewon.mymandalart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaewon.mymandalart.R
import com.jaewon.mymandalart.data.Matrix9Data
import com.jaewon.mymandalart.databinding.Matrix9Binding

class MainMdlAdapter : RecyclerView.Adapter<MainMdlAdapter.MainMdlViewHolder>() {
    var m9List = mutableListOf<Matrix9Data>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMdlViewHolder {
        return MainMdlViewHolder(Matrix9Binding
            .inflate(LayoutInflater.from(parent.context),parent,false), parent.context)
    }

    override fun getItemCount(): Int {
        return m9List.size
    }



    override fun onBindViewHolder(holder: MainMdlViewHolder, position: Int) {
        holder.bind(m9List[position])
    }

    class MainMdlViewHolder(private val binding: Matrix9Binding, parentContext : Context) : RecyclerView.ViewHolder(binding.root) {

        val parentContext : Context = parentContext
        @SuppressLint("SetTextI18n")
        fun bind(m9Data: Matrix9Data) {
            val subMdlAdapter = SubMdlAdapter()

//            subMdlAdapter.m9Num = m9Data.m9Num
            subMdlAdapter.osList = m9Data.osList
            binding.gridMatrix.apply {
                adapter = subMdlAdapter
                layoutManager = GridLayoutManager(binding.gridMatrix.context, 3)
            }
            binding.gridMatrix.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener{
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    if (e.action == MotionEvent.ACTION_DOWN){
//                        m9Data.osList[4].checkOS()
                        val child: View = rv.findChildViewUnder(e.x,e.y) ?: return false
                        val position = rv.getChildLayoutPosition(child)
                        m9Data.osList[position].switchCheckingOS()
                        rv.adapter?.notifyItemChanged(position)
                        Toast.makeText(parentContext,"haha",Toast.LENGTH_SHORT).show()
                        return true
                    }
                    return false
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
            Log.d("ThreadTest", "MMA bind thread ${Thread.currentThread().name}")
            Log.d("GridTestMMA", "MMA bind ${m9Data.m9Num}")
        }


    }
}