package com.rxretrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rxretrofit.model.RetroRxModel
import com.rxretrofit.viewmodel.APIService
import com.rxretrofit.viewmodel.RetroRXViewModel
import io.reactivex.Single
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RetroRXViewModelToTest {

    // A JUnit Test Rule that swaps the background executor used by
    // the Architecture Components with a different one which executes each task synchronously.
    // You can use this rule for your host side tests that use Architecture Components.
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    lateinit var apiService: APIService

    lateinit var retroRXViewModel: RetroRXViewModel

    private lateinit var single: Single<List<RetroRxModel>>

    private  var loading: Boolean = false

    @Before
    fun setUp() {
        // initialize the ViewModed with a mocked github api
        retroRXViewModel = RetroRXViewModel(apiService)
    }

    @Test
    fun fetchRetroInfoTest_success(){
        var retroRxModel = RetroRxModel("tile","body","1")
        var retroRXModelList = ArrayList<RetroRxModel>()
        retroRXModelList.add(retroRxModel)
        single =  Single.just(retroRXModelList)

            if(apiService!=null){
                Mockito.`when`(apiService.makeRequest()).thenReturn(single)
            }


        retroRXViewModel.fetchRetroInfo()
        Assert.assertEquals(1,retroRXViewModel.mutableLiveData.value?.size)
        Assert.assertEquals(loading,retroRXViewModel.loading.value)
    }


    @Test
    fun fetchRetroInfoTest_Failure_Scenario(){
        single = Single.error(Throwable())
        Mockito.`when`(apiService.makeRequest()).thenReturn(single)
        retroRXViewModel.fetchRetroInfo()
        Assert.assertEquals(loading,retroRXViewModel.loading.value)
        //Assert.assertNull(object : Any??)
    }
}