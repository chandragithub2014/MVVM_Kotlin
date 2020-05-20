package com.mvvmcore.repository

import com.mvvmcore.data.PostModel

interface PostDataSource {

    suspend fun add(postModel: PostModel)
    suspend fun get(id:Long) : PostModel?
    suspend fun getAll(): List<PostModel>?
    suspend fun remove(postModel: PostModel)
}