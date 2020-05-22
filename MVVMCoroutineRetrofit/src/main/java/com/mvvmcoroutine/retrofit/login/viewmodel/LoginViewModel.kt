package com.mvvmcoroutine.retrofit.login.viewmodel

import androidx.lifecycle.*
import com.mvvmcoroutine.retrofit.login.model.LoginModel
import com.mvvmcoroutine.retrofit.login.model.TokenModel
import com.mvvmcoroutine.retrofit.network.NetworkAPIService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val apiService: NetworkAPIService
) : ViewModel(), LifecycleObserver {

    var loading : MutableLiveData<Boolean> = MutableLiveData()
    private val errorOnAPI = MutableLiveData<String>()
    var responseToken : MutableLiveData<TokenModel> = MutableLiveData()

     fun validateLogin(loginModel: LoginModel){

         viewModelScope.launch(dispatcher) {
             try{
               val response = apiService.validateLogin(loginModel)
                 if(response.isSuccessful) {
                     responseToken.postValue(response.body())
                     loading.postValue(false)
                 }else{
                     loading.postValue(false)

                   // println("Response Error body during failure is ${JSONObject(response.errorBody()?.string()).get("error")}  ")
                     val errorMessage  = JSONObject(response.errorBody()?.string()).get("error").toString()
                     println("Response Error Message $errorMessage")
                     errorOnAPI.postValue("$errorMessage")
                 }

             }catch (e : Exception){
                 loading.postValue(false)
                 errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
             }
         }
     }


    fun fetchError(): LiveData<String> = errorOnAPI
    fun fetchLoadStatus(): LiveData<Boolean> = loading
    fun fetchTokenStatus():LiveData<TokenModel>  = responseToken

    private  fun onError(message: String){
        errorOnAPI.value = message
        loading.value = false


    }
}