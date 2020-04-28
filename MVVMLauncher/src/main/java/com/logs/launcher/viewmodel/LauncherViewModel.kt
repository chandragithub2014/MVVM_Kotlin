package com.logs.launcher.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*

class LauncherViewModel : ViewModel(),LifecycleObserver{

    var launcherList = mutableListOf<String>()
    var launcherMutableLiveData:MutableLiveData<MutableList<String>> = MutableLiveData<MutableList<String>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchLauncherList(){
        initializeLauncherList()
        launcherMutableLiveData.value = launcherList
    }
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun initializeLauncherList() {
        if(launcherList.size>0){
            launcherList = mutableListOf<String>()
        }
        launcherList.add("MVVM Login")
        launcherList.add("MVVM Room")
        launcherList.add("MVVM Network")
        launcherList.add("MVVM DataBinding")
        launcherList.add("MVVM DataBindingList")
        launcherList.add("MVVM Navigation")
        launcherList.add("MVVM Coroutines")
        launcherList.add("ConstructorInjection")
        launcherList.add("FieldInjection")
        launcherList.add("InterfaceInjection")
        launcherList.add("NamedInjection")

    }




}