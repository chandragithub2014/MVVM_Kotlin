package com.logs.launcher.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.logs.launcher.R

class LauncherViewHolder(parent: ViewGroup,adapterClickListener: AdapterClickListener?) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
    R.layout.launcher_list_item, parent, false)){
    private val nameView = itemView.findViewById<TextView>(R.id.postId)
    var adapterClickListener: AdapterClickListener? = null

    init {
        this.adapterClickListener = adapterClickListener
    }
    fun bindTo(launcher: String?) {
        Log.d("Adapter", "Rebind.....")
        nameView.text = launcher
        itemView.setOnClickListener {
            adapterClickListener?.adapterClicked(adapterPosition, launcher)
        }
    }
}