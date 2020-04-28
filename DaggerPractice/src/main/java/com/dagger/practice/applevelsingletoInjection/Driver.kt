package com.dagger.practice.applevelsingletoInjection

import android.util.Log

private const val TAG = "Driver"

class Driver constructor(driverName :String)
 {
     var driverName : String = driverName
    fun printDriverInfo()
    {

        Log.d(TAG,"Driver Info is $this and corresponding Driver Name is ::::$driverName")
    }
 }
