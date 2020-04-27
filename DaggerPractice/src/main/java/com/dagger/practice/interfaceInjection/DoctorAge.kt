package com.dagger.practice.interfaceInjection

import android.util.Log

private const val TAG = "DoctorAge"
class DoctorAge constructor(age:Int) {
    var age:Int = age
    fun displayDoctorAge(){
        Log.d(TAG,"Doctor Age is $age")
    }
}