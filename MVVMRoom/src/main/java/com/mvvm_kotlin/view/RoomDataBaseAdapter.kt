package com.mvvm_kotlin.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.room.db.userRepo.UserInfo

class RoomDataBaseAdapter  : RecyclerView.Adapter<RoomViewHolder>(){

    var userList: MutableList<UserInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder  = RoomViewHolder(parent)


    override fun getItemCount(): Int  =  userList.size



    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bindTo(userList[position])
    }

    fun setList(userList: MutableList<UserInfo>) {
        this.userList = userList
        notifyDataSetChanged()
    }



}