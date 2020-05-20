package com.mvvm.cleanarchitecture.framework

import com.mvvmcore.usecases.AddPost
import com.mvvmcore.usecases.GetPost
import com.mvvmcore.usecases.GetPosts
import com.mvvmcore.usecases.RemovePost

data class UseCases(
    val addPost: AddPost,
    val getPost: GetPost,
    val getPosts: GetPosts,
    val removePost: RemovePost
)