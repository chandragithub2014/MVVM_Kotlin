package com.mvvm.cleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvmcore.data.PostModel
import com.mvvmcore.repository.PostDataRepository
import com.mvvmcore.usecases.AddPost
import com.mvvmcore.usecases.GetPost
import com.mvvmcore.usecases.GetPosts
import com.mvvmcore.usecases.RemovePost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostListViewModel(application : Application)  : AndroidViewModel(application) {

    private  val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val repository = PostDataRepository(RoomPostDataSource(application))
    private val useCases = UseCases(
        AddPost(repository),
        GetPost(repository),
        GetPosts(repository),
        RemovePost(repository)
    )
    private val postsList = MutableLiveData<List<PostModel>>()
    private val isLoading  = MutableLiveData<Boolean>()

    fun fetchPostsFromDB(){
        coroutineScope.launch {
            useCases.getPosts
           postsList.postValue(useCases.getPosts())
            isLoading.postValue(false)
        }

    }


    fun fetchPostsList(): LiveData<List<PostModel>> = postsList

    fun fetchProgress():LiveData<Boolean> = isLoading


}