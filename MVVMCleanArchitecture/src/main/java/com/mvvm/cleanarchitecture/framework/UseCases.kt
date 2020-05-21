package com.mvvm.cleanarchitecture.framework

import com.mvvmcore.usecases.*

data class UseCases(
    val addPost: AddPost,
    val getPost: GetPost,
    val getPosts: GetPosts,
    val removePost: RemovePost,
    val getPostCount: GetPostCount

)