package com.rxretrofit.viewmodel

import androidx.lifecycle.*
import com.rxretrofit.DI.DaggerRetroRxComponent
import com.rxretrofit.model.RetroRxModel
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject

class RetroCoroutineViewModel  : ViewModel(),LifecycleObserver{



    //CoRoutine API Call
    private var job: Job?=null
    private val exceptionHandler = CoroutineExceptionHandler{
            _, throwable ->

        onError("Exception : ${throwable.localizedMessage}")
    }

    private val errorOnAPI = MutableLiveData<String>()
    private val mutableLiveData:MutableLiveData<List<RetroRxModel>> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var retrofit: Retrofit

    init {
        DaggerRetroRxComponent.create().inject(this)
        loading.value = true
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchResponseFromAPI(){
        job =   CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

          val response = retrofit.create(APIService::class.java).fetchPosts()
          withContext(Dispatchers.Main) {
              try {
              if (response.isSuccessful) {

                  mutableLiveData.value = response.body()
                  loading.value = false

              } else {
                  onError("Response Error : ${response.message()}")
                  loading.value = false
              }
              }catch (e:Exception){
                  println("In Catch()......${e.printStackTrace()}")

                  //onError("Response Error : ${e.localizedMessage}")

              }
          }

        }

    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


    fun fetchError(): LiveData<String> = errorOnAPI

    fun fetchAPIResponse():LiveData<List<RetroRxModel>> = mutableLiveData

    fun fetchLoadStatus():LiveData<Boolean> = loading

   private  fun onError(message: String){

           errorOnAPI.value = message
           loading.value = false
           job?.cancel()

    }
}