package com.mvvm_kotlin.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_kotlin.R
import com.room.db.userRepo.UserInfo


class RoomViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.room_list_item, parent, false)){
    private val nameView = itemView.findViewById<TextView>(R.id.name)
    private val cityView = itemView.findViewById<TextView>(R.id.city)
    private  val ageView = itemView.findViewById<TextView>(R.id.age)
    private  val mobileView = itemView.findViewById<TextView>(R.id.mobile)



    fun bindTo(launcher: UserInfo?) {
        Log.d("Adapter", "Rebind.....")
        nameView.text = launcher?.username
        cityView.text = launcher?.city
        ageView.text = (launcher?.age.toString())
        mobileView.text = launcher?.phone

    }
}