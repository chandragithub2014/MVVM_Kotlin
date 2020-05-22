package com.mvvmcoroutine.retrofit.userlist.viewmodel

import androidx.lifecycle.*
import com.mvvmcoroutine.retrofit.network.NetworkAPIService
import com.mvvmcoroutine.retrofit.userlist.model.Data
import com.mvvmcoroutine.retrofit.userlist.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class UserListViewModel(private val dispatcher: CoroutineDispatcher,
                        private val apiService: NetworkAPIService
):ViewModel(),LifecycleObserver {

    var loading : MutableLiveData<Boolean> = MutableLiveData()
    private val errorOnAPI = MutableLiveData<String>()
    var userListMutableLiveData  = MutableLiveData<List<Data>>()


    fun fetchUserListInfo(page : Int) {
        viewModelScope.launch(dispatcher) {
            try{
                val response = apiService.fetchUsers(page)
                if(response.isSuccessful){
                    userListMutableLiveData.postValue(response.body()?.data)
                    loading.postValue(false)
                }else{
                    loading.postValue(false)
                    errorOnAPI.postValue("Something went wrong::${response.message()}")
                }

            }catch (e : Exception){
                loading.postValue(false)
                errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
            }

        }
    }


    fun fetchError(): LiveData<String> = errorOnAPI
    fun fetchLoadStatus(): LiveData<Boolean> = loading
    fun fetchUsersLiveData():LiveData<List<Data>> = userListMutableLiveData

}