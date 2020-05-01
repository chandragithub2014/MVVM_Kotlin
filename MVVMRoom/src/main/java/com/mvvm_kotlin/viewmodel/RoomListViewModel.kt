package com.mvvm_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.room.db.database.RepositoryDB
import com.room.db.userRepo.UserInfo
import kotlinx.coroutines.*

class RoomListViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {

    var userList = mutableListOf<UserInfo>()

    var userFinalList:LiveData<MutableList<UserInfo>> = MutableLiveData<MutableList<UserInfo>>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { RepositoryDB.getInstance(getApplication()).userDao() }
    val error = MutableLiveData<String>()
    val insertedId =  MutableLiveData<Long>()
    var scope = viewModelScope


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchRoomData() {
        scope.launch {
        userFinalList = db.getAllPosts()
        }

    }

     fun insertUserInfo(userInfo: UserInfo){
         if(userInfo.username.isNullOrEmpty() ||
                 userInfo.passwordHash.toString().isNullOrEmpty() ||
                 userInfo.city.isNullOrEmpty() ||
                 userInfo.age.toString().isNullOrEmpty() ||
                 userInfo.phone.isNullOrEmpty()){
             error.value = "Input Fields cannot be Empty"
         }
         else {
             coroutineScope.launch {
                 val user = db.getUserInfo(userInfo.username)
                 if (user != null) {
                     withContext(Dispatchers.Main) {
                         error.value = "User Already Exists"
                     }

                 } else {
                     val userId: Long = db.insertRow(userInfo)
                     withContext(Dispatchers.Main) {
                         insertedId.value = userId
                     }
                 }


             }
         }
     }


     private suspend fun insertData() {
        if (userList.size > 0) {
            userList.clear()
            userList = mutableListOf<UserInfo>()
        }
        for (i in 1..10) {
            userList.add(
                UserInfo(
                    "Name${i}",
                    "password${i}".hashCode(),
                    (i + 10),
                    "City${i}",
                    "123456789${i}"
                )
            )
        }
        var size = userList.size
         db.insertAll(userList)
    }


    override fun onCleared() {
        super.onCleared()
      //  scope.cancel()
    }
   /* private suspend fun  insertList(){
        db.insertAll(userList)

    }*/

    fun insertUser(userInfo: UserInfo){
        scope.launch {
        if(userInfo.username.isNullOrEmpty() ||
            userInfo.passwordHash.toString().isNullOrEmpty() ||
            userInfo.city.isNullOrEmpty() ||
            userInfo.age.toString().isNullOrEmpty() ||
            userInfo.phone.isNullOrEmpty()){
         //   error.value = "Input Fields cannot be Empty"
            error.postValue("Input Fields cannot be Empty")
        }else{
            val user = db.getUserInfo(userInfo.username)
            if (user != null) {

                   // error.value = "User Already Exists"
                error.postValue("User Already Exists")


            } else {
                val userId: Long = db.insertRow(userInfo)
                insertedId.postValue(userId)
            }
        }
        }
    }


}