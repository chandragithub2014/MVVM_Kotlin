package com.dagger.practice.singletonInjection

import android.util.Log

private const val TAG = "Driver"
class Driver constructor(driverName: String){


    var driverName:String = driverName

    fun displayDriverInfo(){
        Log.d(TAG,"Driver information is $this and name is $driverName")
    }
}