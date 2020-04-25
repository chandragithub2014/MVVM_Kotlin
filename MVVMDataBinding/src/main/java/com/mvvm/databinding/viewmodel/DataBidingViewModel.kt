package com.mvvm.databinding.viewmodel

import androidx.lifecycle.*
import java.text.FieldPosition

class DataBidingViewModel  : ViewModel(),LifecycleObserver{

   private val isEnabled:MutableLiveData<Boolean> = MutableLiveData<Boolean>()
   private val empPhoneNumber:MutableLiveData<String> = MutableLiveData<String>()
    private val fullName:MutableLiveData<String> = MutableLiveData<String>()
    private val empMutableList:MutableLiveData<MutableList<String>>  =  MutableLiveData()
   private  val imgURLList:MutableList<String> = mutableListOf()

    var empId:MutableLiveData<String> = MutableLiveData<String>()

    fun fetchIsEnabled():LiveData<Boolean> =  isEnabled

    fun fetchMobileNumber():LiveData<String> = empPhoneNumber

    fun fetchFullName():LiveData<String> = fullName
    fun fetchEmpList():LiveData<MutableList<String>> = empMutableList

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateExecuted(){
        println("On Created Executed")
    }
    fun onFetchClicked(){
       val empIdLatestVal= empId.value
        if(empIdLatestVal == "123"){
            fullName.value = "ABC DEG"
            empPhoneNumber.value = "9885750548"
            isEnabled.value = true


        }
    }

    fun createEmpList(){
        val empList : MutableList<String>  = mutableListOf()
        empList.add("ABC")
        empList.add("DEF")
        empList.add("EFG")
        for (i in 1..10){
            empList.add("EMP$i")
        }
        empMutableList.value = empList
        createImageURLList()
    }

    fun fetchEmpList(position : Integer) : String {
       return  empMutableList.value?.get(position.toInt()).toString()
    }


    private fun createImageURLList(){



        imgURLList.add("https://via.placeholder.com/50x50.png?text=1")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=2")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=3")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=4")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=1")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=2")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=3")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=4")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=3")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=2")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=1")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=4")
        imgURLList.add("https://via.placeholder.com/50x50.png?text=1")
    }

    fun fetchImageURL(position: Integer):String = imgURLList[position.toInt()].toString()


}




