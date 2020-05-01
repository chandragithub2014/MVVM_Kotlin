package com.rxretrofit.viewmodel

import com.rxretrofit.model.RetroRxModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
//  https://jsonplaceholder.typicode.com/posts
    @GET("/posts")
    fun makeRequest(): Single<List<RetroRxModel>>

    @GET("/posts")
    suspend fun  fetchPosts(): Response<List<RetroRxModel>>


    @GET("/posts")
    suspend fun  fetchUserPosts(): Response<List<RetroRxModel>>
}