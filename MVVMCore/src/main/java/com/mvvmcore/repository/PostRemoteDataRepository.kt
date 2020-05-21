package com.mvvmcore.repository

class PostRemoteDataRepository(private val postRemoteDataSource: PostRemoteDataSource) {
    suspend fun fetchRemoteData() = postRemoteDataSource.getAllRemote()
}