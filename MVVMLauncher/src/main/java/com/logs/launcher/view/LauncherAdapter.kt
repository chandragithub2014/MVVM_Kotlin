package com.logs.launcher.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LauncherAdapter(adapterClickListener: AdapterClickListener) : RecyclerView.Adapter<LauncherViewHolder>() {
    var launcherList: MutableList<String> = mutableListOf()
    var adapterClickListener: AdapterClickListener? = null

    init {
        this.adapterClickListener = adapterClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauncherViewHolder =
        LauncherViewHolder(parent,adapterClickListener)


    override fun getItemCount(): Int = launcherList.size



    override fun onBindViewHolder(holder: LauncherViewHolder, position: Int) {
        holder.bindTo(launcherList[position])
    }

    fun setList(launcherList: MutableList<String>) {
        this.launcherList = launcherList
        notifyDataSetChanged()
    }


}