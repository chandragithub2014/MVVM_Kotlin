package com.rxretrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rxretrofit.model.RetroRxModel
import com.rxretrofit.viewmodel.APIService
import com.rxretrofit.viewmodel.RetroRXViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.powermock.api.easymock.PowerMock
import org.powermock.api.easymock.PowerMock.expectPrivate
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Retrofit
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class RetroRXViewModelTest {
    // The below rule executes the tasks instantly without waiting on BackGround or mainThread
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofit: Retrofit



   @Mock
    lateinit var apiService: APIService

    @InjectMocks
    lateinit var retroRXViewModel: RetroRXViewModel

    private lateinit var single: Single<List<RetroRxModel>>

    private  var loading: Boolean = false
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetchRetroInfoTest_success(){
        var retroRxModel = RetroRxModel("tile","body","1")
        var retroRXModelList = ArrayList<RetroRxModel>()
        retroRXModelList.add(retroRxModel)
        single =  Single.just(retroRXModelList)
        if(retrofit!=null){
            if(apiService!=null){
                Mockito.`when`(apiService.makeRequest()).thenReturn(single)
            }
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


    /*@Test
    fun `test View Model On Cleared`(){
    //    retroRXViewModel.
    }*/

    @Before
    fun setUpRXSchedulers(){
        retroRXViewModel = RetroRXViewModel()
        var immediateThinScheduler  = object: Scheduler(){

            override fun createWorker(): Worker {
                return  ExecutorScheduler.ExecutorWorker(Executor { it.run() },true)
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediateThinScheduler }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediateThinScheduler }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediateThinScheduler }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediateThinScheduler }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { _ -> immediateThinScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> immediateThinScheduler }

    }




}