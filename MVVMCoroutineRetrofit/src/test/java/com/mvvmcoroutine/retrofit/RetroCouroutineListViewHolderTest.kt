package com.mvvmcoroutine.retrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mvvmcoroutine.retrofit.login.model.LoginModel
import com.mvvmcoroutine.retrofit.login.model.TokenModel
import com.mvvmcoroutine.retrofit.login.viewmodel.LoginViewModel
import com.mvvmcoroutine.retrofit.network.NetworkAPIService
import com.mvvmcoroutine.retrofit.userlist.model.Ad
import com.mvvmcoroutine.retrofit.userlist.model.Data
import com.mvvmcoroutine.retrofit.userlist.model.RetroResult
import com.mvvmcoroutine.retrofit.userlist.viewmodel.UserListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class RetroCouroutineListViewHolderTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var apiService: NetworkAPIService

    private  var loading: Boolean = false

    lateinit var listViewModel: UserListViewModel

    private lateinit var response: Response<RetroResult>


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

    }


    val testDispatcher = TestCoroutineDispatcher()
    @Test
    fun `check list viewmodel to fetch  data correctly`() = testDispatcher.runBlockingTest{
        var data = Data(1,"george.bluth@reqres.in","George","Bluth","https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg")
        var ad = Ad("http://statuscode.org/","StatusCode Weekly","A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.")
        var list = arrayListOf<Data>()
        list.add(data)
        var retroResponse = RetroResult(1,6,12,2,list,ad)

        response = Response.success(retroResponse)
        listViewModel = UserListViewModel(testDispatcher,apiService)
        if (retrofit != null) {

            if (apiService != null) {
                Mockito.`when`(apiService.fetchUsers(2)).thenReturn(response)
            }
        }

        listViewModel.fetchUserListInfo(2)
        // advanceTimeBy(15_000)
        println("Loading Val::::${listViewModel.fetchLoadStatus()?.value}")
        println("PostLive Dat::::${listViewModel.fetchUsersLiveData().value}")
        Assert.assertEquals(1,listViewModel.fetchUsersLiveData().value?.size)
        Assert.assertEquals(false, listViewModel.fetchLoadStatus()?.value)



    }

    /*@Test
    fun `check list viewmodel to fetch  data Incorrectly`() = testDispatcher.runBlockingTest{

        listViewModel = UserListViewModel(testDispatcher,apiService)
        val mockException: HttpException =  mock()
        if (retrofit != null) {

            if (apiService != null) {
                Mockito.`when`(apiService.fetchUsers(2)).thenThrow(mockException)
            }
        }
        listViewModel.fetchUserListInfo(2)
        Assert.assertEquals(false, listViewModel.fetchLoadStatus()?.value)

    }*/
}