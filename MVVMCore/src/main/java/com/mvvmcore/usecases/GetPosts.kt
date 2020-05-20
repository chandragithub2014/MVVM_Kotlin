package com.mvvmcore.usecases

import com.mvvmcore.repository.PostDataRepository

class GetPosts(private  val postDataRepository: PostDataRepository) {
    suspend operator  fun invoke()=postDataRepository.getPosts()
}