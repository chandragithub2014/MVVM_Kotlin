package com.mvvmcore.usecases

import com.mvvmcore.repository.PostDataRepository
import com.mvvmcore.repository.PostRemoteDataRepository

class FetchRemotePosts(private  val postRemoteDataRepository: PostRemoteDataRepository) {
    suspend operator fun invoke() = postRemoteDataRepository.fetchRemoteData()
}