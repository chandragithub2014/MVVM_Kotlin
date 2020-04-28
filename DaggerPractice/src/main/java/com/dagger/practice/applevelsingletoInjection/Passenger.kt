package com.dagger.practice.applevelsingletoInjection

import android.util.Log


private const val TAG = "Passenger"
@PerActivity
class Passenger constructor( driver:Driver , passengerName:String) {
    var passengerName:String = passengerName
    var driver : Driver = driver

    fun displayPassengerInfo(){
        Log.d(TAG,"Passenger instance  $this with name  $passengerName  and driver instance is :::$driver ::: and driver Name is ${driver.driverName}")
    }

}