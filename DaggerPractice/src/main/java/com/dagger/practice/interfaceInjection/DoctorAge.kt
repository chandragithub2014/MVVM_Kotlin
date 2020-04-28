package com.dagger.practice.interfaceInjection

import android.util.Log
import javax.inject.Inject

private const val TAG = "DoctorAge"
class DoctorAge  constructor(age:Integer, year:Integer) {
    var age:Integer = age
    var  year:Integer = year
    fun displayDoctorAge(){
        Log.d(TAG,"Doctor Age is $age and Year of Birth is $year")
    }
}