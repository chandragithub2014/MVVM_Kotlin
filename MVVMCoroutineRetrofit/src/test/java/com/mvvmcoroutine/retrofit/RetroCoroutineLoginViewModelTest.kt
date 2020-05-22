package com.mvvmcoroutine.retrofit

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.JsonObject
import com.mvvmcoroutine.retrofit.login.model.LoginModel
import com.mvvmcoroutine.retrofit.login.model.TokenModel
import com.mvvmcoroutine.retrofit.login.viewmodel.LoginViewModel
import com.mvvmcoroutine.retrofit.network.NetworkAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.math.exp

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class RetroCoroutineLoginViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var apiService: NetworkAPIService

    lateinit var loginViewModel: LoginViewModel



    private  var loading: Boolean = false
    private lateinit var response:Response<TokenModel>




    @Before
   fun setUp(){
        MockitoAnnotations.initMocks(this)

   }




val testDispatcher = TestCoroutineDispatcher()
    @Test
    fun `check login viewmodel validates data correctly`() = testDispatcher.runBlockingTest{
        var loginModel = LoginModel("eve.holt@reqres.in","cityslicka")
        loginViewModel = LoginViewModel(testDispatcher,apiService)
        response = Response.success(TokenModel("QpwL5tke4Pnpja7X4",""))
        if (retrofit != null) {

            if (apiService != null) {
                Mockito.`when`(apiService.validateLogin(loginModel)).thenReturn(response)
            }
        }

            loginViewModel.validateLogin(loginModel)
           // advanceTimeBy(15_000)
            println("Loading Val::::${loginViewModel.fetchLoadStatus()?.value}")
            println("PostLive Dat::::${loginViewModel.fetchTokenStatus().value}")
            Assert.assertEquals("QpwL5tke4Pnpja7X4",loginViewModel.fetchTokenStatus().value?.token)
            Assert.assertEquals(false, loginViewModel.fetchLoadStatus()?.value)



    }

    @Test
    fun `check login viewmodel validation failed condition `() = testDispatcher.runBlockingTest {

        var loginModel = LoginModel("eve.holt@reqre.in","cityslicka")
        loginViewModel = LoginViewModel(testDispatcher,apiService)

        response = Response.success(TokenModel("","user Not Found"))
        //Response.err
                    //Response.error(400, ResponseBody.create("JSON",JsonObject().addProperty("error","user not found")))
        if (retrofit != null) {

            if (apiService != null) {
                Mockito.`when`(apiService.validateLogin(loginModel)).thenReturn(response)
            }
        }

        loginViewModel.validateLogin(loginModel)
        // advanceTimeBy(15_000)
        println("Loading Val::::${loginViewModel.fetchLoadStatus()?.value}")
        println("PostLive Dat::::${loginViewModel.fetchTokenStatus().value}")
        Assert.assertEquals("user Not Found",loginViewModel.fetchTokenStatus().value?.error)
        Assert.assertEquals(false, loginViewModel.fetchLoadStatus()?.value)

    }

}