package com.mvvm.common.di


import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetroRxModule {


    @Provides
    fun  provideRetroInfo() : Retrofit{
       return  Retrofit
           .Builder()
           .baseUrl(RetroURL.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .build()

    }
}