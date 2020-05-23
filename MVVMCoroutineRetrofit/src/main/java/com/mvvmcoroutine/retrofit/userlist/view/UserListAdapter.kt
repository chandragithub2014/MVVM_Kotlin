package com.mvvmcoroutine.retrofit.userlist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvmcoroutine.retrofit.R
import com.mvvmcoroutine.retrofit.userlist.ItemClickListener
import com.mvvmcoroutine.retrofit.userlist.model.Data
import com.mvvmcoroutine.retrofit.userlist.model.User
import kotlinx.android.synthetic.main.item_user_list.view.*


class UserListAdapter(var users: ArrayList<Data>,var context: Context,var itemClickListener: ItemClickListener) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder  =
    UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_list,parent,false))

    override fun getItemCount(): Int  = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int)  = holder.bind(users[position])

    fun refreshAdapter( newUsers:List<Data>){
       users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    inner  class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private  val layout = view.item_layout
        private  val firstName = view.first_name
        private val lastName = view.last_name
        private val email = view.email
        private val image = view.imageView
        fun bind(userModel: Data){
            firstName.text = userModel.first_name
            lastName.text = userModel.last_name
            email.text = userModel.email
            Glide.with(context).load(userModel.avatar).placeholder(R.drawable.ic_sync).into(image)
            layout.setOnClickListener {
                itemClickListener.setClickedInfo(userModel)
            }


        }
    }
}