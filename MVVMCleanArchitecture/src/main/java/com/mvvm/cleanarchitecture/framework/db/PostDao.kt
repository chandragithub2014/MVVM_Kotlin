package com.mvvm.cleanarchitecture.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mvvmcore.data.PostModel



@Dao
interface PostDao {

    @Insert(onConflict = REPLACE)
    suspend fun addPostEntity(postEntity: PostEntity)

    @Query("SELECT * FROM post where id = :id")
    suspend fun  getPostEntity(id:Long) :  PostEntity?

    @Query("SELECT * FROM post")
    suspend fun getAllPostEntities():List<PostEntity>

    @Delete
    suspend fun removePost(postEntity: PostEntity)
}