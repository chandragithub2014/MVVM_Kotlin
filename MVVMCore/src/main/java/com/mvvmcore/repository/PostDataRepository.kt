package com.mvvmcore.repository

import com.mvvmcore.data.PostModel

class PostDataRepository(private val postDataSource: PostDataSource) {
    suspend fun addPost(postModel: PostModel) = postDataSource.add(postModel)
    suspend fun getPost(id:Long) = postDataSource.get(id)
    suspend fun getPosts() = postDataSource.getAll()
    suspend fun removePost(postModel: PostModel) = postDataSource.remove(postModel)
}