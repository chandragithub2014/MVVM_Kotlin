package com.mvvm.cleanarchitecture.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mvvmcore.data.PostModel

@Entity(tableName = "post")
data class PostEntity(
    val title: String,
    val body: String,

    @ColumnInfo(name = "creation_date")
    val creationTime: Long = 0,
    @ColumnInfo(name = "update_time")
    val updateTime: Long = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    companion object {
        fun fromPost(postModel: PostModel) = PostEntity(
            postModel.title,
            postModel.body,
            postModel.creationTime,
            postModel.updateTime
        )
    }

    fun toPost() = PostModel(title,body,id,creationTime,updateTime)
}