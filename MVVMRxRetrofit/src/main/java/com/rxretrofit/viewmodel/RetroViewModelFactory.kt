package com.rxretrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rxretrofit.DI.DaggerRetroRxComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Inject

class RetroViewModelFactory : ViewModelProvider.Factory {
    @Inject
    lateinit var retrofit: Retrofit
    lateinit var apiService: APIService

    @ExperimentalStdlibApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        DaggerRetroRxComponent.create().inject(this)
        apiService = retrofit.create(APIService::class.java)

        if (modelClass.isAssignableFrom(RetroRXViewModel::class.java)) {
            return RetroRXViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}