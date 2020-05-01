package com.rxretrofit

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rxretrofit.model.RetroRxModel
import com.rxretrofit.viewmodel.APIService
import com.rxretrofit.viewmodel.RetroCoroutineViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.math.exp

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class RetroCoroutineViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var apiService: APIService

    lateinit var retroCoroutineViewModel: RetroCoroutineViewModel

    private lateinit var response: Response<List<RetroRxModel>>

    private  var loading: Boolean = false
 /*   @Mock
    private lateinit var apiUsersObserver: Observer<List<RetroRxModel>>
*/




    @Before
   fun setUp(){
        MockitoAnnotations.initMocks(this)

   }



    @Test
    fun fetchRetroInfoTest_success(){

        testCoroutineRule.runBlockingTest {
            retroCoroutineViewModel = RetroCoroutineViewModel(TestCoroutineDispatcher(),apiService)
            var retroRxModel = RetroRxModel("tile", "body", "1")
            var retroRXModelList = ArrayList<RetroRxModel>()
            retroRXModelList.add(retroRxModel)
            response = Response.success(retroRXModelList)
            if (retrofit != null) {

                if (apiService != null) {
                    Mockito.`when`(apiService.fetchUserPosts()).thenReturn(response)
                    retroCoroutineViewModel.fetchResponseFromAPI()

                }
            }
            val funResponse = retroCoroutineViewModel.fetchPostLiveData()
            println("Response::::${funResponse.value}")
         //   Assert.assertNotNull(funResponse.value)
             Assert.assertEquals(1,funResponse.value?.size)
            Assert.assertEquals(loading,retroCoroutineViewModel.loading)

          //  Assert.assertEquals(funResponse.value?.size,100)

        }
    }
val testDispatcher = TestCoroutineDispatcher()
    @Test /*(expected = TimeoutCancellationException::class)*/
    fun `check viewmodel fetches data correctly`() = testDispatcher.runBlockingTest{
        var retroRxModel = RetroRxModel("tile", "body", "1")
        var retroRXModelList = ArrayList<RetroRxModel>()
        retroRXModelList.add(retroRxModel)
        response = Response.success(retroRXModelList)
        retroCoroutineViewModel = RetroCoroutineViewModel(testDispatcher,apiService)
        if (retrofit != null) {

            if (apiService != null) {
                Mockito.`when`(apiService.fetchUserPosts()).thenReturn(response)
            }
        }

            retroCoroutineViewModel.fetchResponseFromAPI()
           // advanceTimeBy(15_000)
            println("Loading Val::::${retroCoroutineViewModel.fetchLoadStatus()?.value}")
            println("PostLive Dat::::${retroCoroutineViewModel.fetchPostLiveData().value}")
               Assert.assertEquals(1,retroCoroutineViewModel.fetchPostLiveData().value?.size)
            Assert.assertEquals(false, retroCoroutineViewModel.loading?.value)



    }



}