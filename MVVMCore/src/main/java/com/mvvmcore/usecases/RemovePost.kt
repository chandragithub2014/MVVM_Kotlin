package com.mvvmcore.usecases

import com.mvvmcore.data.PostModel
import com.mvvmcore.repository.PostDataRepository

class RemovePost(private val postDataRepository: PostDataRepository) {
    suspend operator fun invoke(postModel: PostModel) = postDataRepository.removePost(postModel)
}