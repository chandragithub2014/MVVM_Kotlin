package com.mvvm.cleanarchitecture.framework

import android.content.Context
import com.mvvm.cleanarchitecture.framework.db.DataBaseService
import com.mvvm.cleanarchitecture.framework.db.PostEntity
import com.mvvmcore.data.PostModel
import com.mvvmcore.repository.PostDataSource

class RoomPostDataSource(context: Context) : PostDataSource {

    val postDao = DataBaseService.getInstance(context).postDao()

    override suspend fun add(postModel: PostModel) = postDao.addPostEntity(PostEntity.fromPost(postModel))


    override suspend fun get(id: Long) = postDao.getPostEntity(id)?.toPost()

    override suspend fun getAll() = postDao.getAllPostEntities().map { it.toPost() }

    override suspend fun remove(postModel: PostModel)  = postDao.removePost(PostEntity.fromPost(postModel))
}