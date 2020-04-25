package com.mvvmlogin.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvmlogin.model.LoginModel

class LoginViewModel : ViewModel(),LifecycleObserver{


    val loginStatus = MutableLiveData<String>()


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
}