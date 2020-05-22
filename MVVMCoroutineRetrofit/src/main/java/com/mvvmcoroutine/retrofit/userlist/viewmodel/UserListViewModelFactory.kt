package com.mvvmcoroutine.retrofit.userlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvmcoroutine.retrofit.di.DaggerNetworkComponent
import com.mvvmcoroutine.retrofit.login.viewmodel.LoginViewModel
import com.mvvmcoroutine.retrofit.network.NetworkAPIService
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Inject

class UserListViewModelFactory : ViewModelProvider.Factory {
    @Inject
    lateinit var retrofit: Retrofit
    lateinit var networkAPIService: NetworkAPIService
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        DaggerNetworkComponent.create().inject(this)
        networkAPIService = retrofit.create(NetworkAPIService::class.java)
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(Dispatchers.Main,networkAPIService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}