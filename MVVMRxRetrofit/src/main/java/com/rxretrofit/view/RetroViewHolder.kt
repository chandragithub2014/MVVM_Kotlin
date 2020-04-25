package com.rxretrofit.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rxretrofit.R
import com.rxretrofit.model.RetroRxModel


class RetroViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.retro_list_item, parent, false)){
    private val postId = itemView.findViewById<TextView>(R.id.postId)
    private val postTitle = itemView.findViewById<TextView>(R.id.postTitle)
    private  val postBody = itemView.findViewById<TextView>(R.id.postBody)



    fun bindTo(retroRxModel: RetroRxModel?) {
        Log.d("Adapter", "Rebind.....")
        retroRxModel?.let {
            postId.text = retroRxModel.id
            postBody.text = retroRxModel.body
            postTitle.text = retroRxModel.title

        }


    }
}