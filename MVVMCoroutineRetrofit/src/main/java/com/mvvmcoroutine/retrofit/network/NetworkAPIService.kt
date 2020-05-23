package com.mvvmcoroutine.retrofit.network

import com.mvvmcoroutine.retrofit.login.model.LoginModel
import com.mvvmcoroutine.retrofit.login.model.TokenModel
import com.mvvmcoroutine.retrofit.userlist.model.RetroResult
import com.mvvmcoroutine.retrofit.userlist.model.RetroResultUser
import com.mvvmcoroutine.retrofit.userlist.model.User
import retrofit2.Response
import retrofit2.http.*

interface NetworkAPIService {

    @POST("/api/login")
    suspend fun validateLogin(@Body loginModel: LoginModel) : Response<TokenModel>

    @GET("/api/users")
    suspend fun fetchUsers(@Query("page") page :Int): Response<RetroResult>

    @GET("/api/users/{id}")
    suspend fun fetchSelectedUsers(@Path("id") id : Int): Response<RetroResultUser>
}