package com.mvvmcore.usecases

import com.mvvmcore.repository.PostDataRepository

class GetPost(private val postDataRepository: PostDataRepository) {
    suspend operator  fun invoke(id:Long) = postDataRepository.getPost(id)
}