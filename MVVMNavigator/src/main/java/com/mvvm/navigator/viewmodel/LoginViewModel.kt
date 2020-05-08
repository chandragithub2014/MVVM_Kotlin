package com.mvvm.navigator.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.navigator.model.LoginModel


class LoginViewModel : ViewModel(),LifecycleObserver{


    var loginStatus = MutableLiveData<String>()
    var loginButtonClicked = MutableLiveData<Boolean>()


    fun validateLogin(loginModel: LoginModel){
       if(loginModel.userName.isNullOrEmpty() || loginModel.password.isNullOrEmpty()){
           loginStatus.value = "User Name or Password cannot be Empty"

       }else if(loginModel.userName.equals("admin",ignoreCase = true) &&
           loginModel.password == "admin"
       ){

           loginStatus.value = "Login Successful"
       }else{

           loginStatus.value = "Login Failed"
       }
    }

    fun setLoginButtonClickedStatus(isClicked : Boolean){
        loginButtonClicked.value = isClicked
    }

    fun fetchLoginStatus():LiveData<String> = loginStatus

    fun fetchLoginButtonClickedStatus():LiveData<Boolean> = loginButtonClicked

    override fun onCleared() {
        super.onCleared()
        loginStatus = MutableLiveData<String>()
    }
}