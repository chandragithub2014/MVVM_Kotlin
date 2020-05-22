package com.mvvmcoroutine.retrofit.network

import com.mvvmcoroutine.retrofit.login.model.LoginModel
import com.mvvmcoroutine.retrofit.login.model.TokenModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkAPIService {

    @POST("/api/login")
    suspend fun validateLogin(@Body loginModel: LoginModel) : Response<TokenModel>
}