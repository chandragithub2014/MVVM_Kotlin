package com.rxretrofit.viewmodel

import androidx.lifecycle.*
import com.rxretrofit.DI.DaggerRetroRxComponent
import com.rxretrofit.model.RetroRxModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import retrofit2.Retrofit
import javax.inject.Inject

class RetroRXViewModel() :ViewModel(), LifecycleObserver {

    var postInfoLiveData: LiveData<List<RetroRxModel>> = MutableLiveData()
    var postLoadError : MutableLiveData<String> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()
    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    val mutableLiveData:MutableLiveData<List<RetroRxModel>> = MutableLiveData()



    @Inject
    lateinit var retrofit: Retrofit

    lateinit var apiService: APIService

    init {
        DaggerRetroRxComponent.create().inject(this)
        loading.value = true
        apiService = retrofit.create(APIService::class.java)
    }

    fun fetchRetroResponseLiveData():LiveData<List<RetroRxModel>>{
        return mutableLiveData
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchRetroInfo(){
        compositeDisposable.add(apiService.makeRequest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<RetroRxModel>>(){
                override fun onSuccess(t: List<RetroRxModel>) {

                    mutableLiveData.value  = t
                    loading.value = false
                }
               override fun onError(e: Throwable) {
                    e.printStackTrace()
                    postLoadError.value = e.message
                    loading.value = false
//                   onError(e.localizedMessage)
                }
            })
        )
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun onError(message:String){
        println(message)
    }
}