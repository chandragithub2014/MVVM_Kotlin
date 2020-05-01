package com.mvvmlogin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mvvmlogin.model.LoginModel
import com.mvvmlogin.viewmodel.LoginViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    /*@Mock
    lateinit var loginModel : LoginModel*/

    lateinit var  loginViewModel: LoginViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel()

    }

    @Test
    fun `validate login for success` (){
        var loginModel = LoginModel("Admin","admin")
        loginViewModel.validateLogin(loginModel)
        Assert.assertEquals("Login Successful",loginViewModel.loginStatus.value)
    }

    @Test
    fun `validate login for Failure` (){
        var loginModel = LoginModel("Admin","Adin")
        loginViewModel.validateLogin(loginModel)
        Assert.assertEquals("Login Failed",loginViewModel.loginStatus.value)
    }


    @Test
    fun `validate login for Empty Fields` (){
        var loginModel = LoginModel("","")
        loginViewModel.validateLogin(loginModel)
        Assert.assertEquals("User Name or Password cannot be Empty",loginViewModel.loginStatus.value)
    }
}