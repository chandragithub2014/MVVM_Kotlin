package com.dagger.practice.daggerapplication

import android.util.Log
import javax.inject.Inject
private const val TAG = "Access"
class Access @Inject constructor(){

    fun setListener(employee : Employee){
        Log.d(TAG,"Access Allowed")
    }
}