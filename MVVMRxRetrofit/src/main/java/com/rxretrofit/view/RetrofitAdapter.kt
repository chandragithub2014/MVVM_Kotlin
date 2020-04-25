package com.rxretrofit.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rxretrofit.model.RetroRxModel

class RetrofitAdapter  : RecyclerView.Adapter<RetroViewHolder>(){

    var postList: List<RetroRxModel> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RetroViewHolder(parent)


    override fun getItemCount(): Int  =  postList.size





    override fun onBindViewHolder(holder: RetroViewHolder, position: Int) {
        holder.bindTo(postList[position])
    }


    fun setList(postList: List<RetroRxModel>) {
        this.postList = postList
        notifyDataSetChanged()
    }
}