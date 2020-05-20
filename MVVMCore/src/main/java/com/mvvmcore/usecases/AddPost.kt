package com.mvvmcore.usecases

import com.mvvmcore.data.PostModel
import com.mvvmcore.repository.PostDataRepository

class AddPost(private  val postDataRepository: PostDataRepository){
    suspend operator  fun invoke(postModel: PostModel) = postDataRepository.addPost(postModel)
}