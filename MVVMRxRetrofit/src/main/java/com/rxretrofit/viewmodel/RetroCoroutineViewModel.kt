package com.rxretrofit.viewmodel

import androidx.lifecycle.*
import com.rxretrofit.DI.DaggerRetroRxComponent
import com.rxretrofit.model.RetroRxModel
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject

class RetroCoroutineViewModel(private val dispatcher: CoroutineDispatcher,private val apiService: APIService)  : ViewModel(),LifecycleObserver{



    //CoRoutine API Call

    private val exceptionHandler = CoroutineExceptionHandler{
            _, throwable ->

        onError("Exception : ${throwable.localizedMessage}")
    }

    private val errorOnAPI = MutableLiveData<String>()
    private val mutableLiveData:MutableLiveData<List<RetroRxModel>> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()

   /* @Inject
    lateinit var retrofit: Retrofit
*/
   // lateinit var apiService: APIService

    init {
      //  DaggerRetroRxComponent.create().inject(this)
        loading.value = true
     //   apiService = retrofit.create(APIService::class.java)
    }



     val postsMutableLiveData:MutableLiveData<List<RetroRxModel>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchResponseFromAPI(){
        viewModelScope.launch (dispatcher){
           // postsMutableLiveData.postValue(null)
            try{

                val response  = apiService.fetchUserPosts()
                if(response.isSuccessful){
                    postsMutableLiveData.postValue(response.body())
                    loading.postValue(false)
                   // loading.value = false
                }else{
                    loading.postValue(false)
                    errorOnAPI.postValue("Something went wrong::${response.message()}")
                }



            }catch (e:Exception){
                loading.postValue(false)
                errorOnAPI.postValue("Something went wrong::${e.localizedMessage}")
            }

        }

    }

    fun fetchPostLiveData(): LiveData<List<RetroRxModel>>  = postsMutableLiveData


    override fun onCleared() {
        super.onCleared()

    }


    fun fetchError(): LiveData<String> = errorOnAPI



    fun fetchLoadStatus():LiveData<Boolean> = loading

   private  fun onError(message: String){
           errorOnAPI.value = message
           loading.value = false


    }
}