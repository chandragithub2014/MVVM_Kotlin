package com.mvvmcoroutine.retrofit.network

import com.mvvmcoroutine.retrofit.login.model.LoginModel
import com.mvvmcoroutine.retrofit.login.model.TokenModel
import com.mvvmcoroutine.retrofit.userlist.model.RetroResult
import com.mvvmcoroutine.retrofit.userlist.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkAPIService {

    @POST("/api/login")
    suspend fun validateLogin(@Body loginModel: LoginModel) : Response<TokenModel>

    @GET("/api/users")
    suspend fun fetchUsers(@Query("page") page :Int): Response<RetroResult>
}