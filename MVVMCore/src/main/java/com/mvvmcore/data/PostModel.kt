package com.mvvmcore.data

data class PostModel(
    var title: String,
    var body: String,
    var id: Long = 0,
    var creationTime: Long = 0,
    var updateTime: Long = 0,
    var postCount : Int = 0
)