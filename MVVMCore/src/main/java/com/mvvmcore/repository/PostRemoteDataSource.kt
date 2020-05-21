package com.mvvmcore.repository

import com.mvvmcore.data.PostModel

interface PostRemoteDataSource {
    suspend fun getAllRemote():List<PostModel>?
}