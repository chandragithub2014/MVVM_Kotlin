package com.mvvm.cleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.cleanarchitecture.R
import com.mvvmcore.data.PostModel
import kotlinx.android.synthetic.main.item_post.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PostListAdapter(var posts: ArrayList<PostModel>, val listClickListener: ListClickListener) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    fun updatePosts(newPosts : List<PostModel>){
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))

    override fun getItemCount(): Int  =   posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(posts[position])


    inner class  PostViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val layout = view.postLayout
        private val title = view.title
        private val content = view.content
        private val  updatedTime = view.date
        fun bind(postModel: PostModel){
            title.text = postModel.title
            content.text = postModel.body
            val sdf = SimpleDateFormat("MMM dd , HH:mm:ss")
            val resultDate = Date(postModel.updateTime)
            updatedTime.text = "Last Updated ${sdf.format(resultDate)}"
            layout.setOnClickListener {
                    listClickListener.onClick(postModel.id)
            }

        }
    }
}