package com.dagger.practice.subComponentInjection

import android.util.Log

private const val TAG = "Sports"
class Sports constructor(sportsCenter : String) {
    var sportsCenter : String = sportsCenter
       fun displaySportsCenterInfo(){
          Log.d(TAG, "Sports Center Info is $sportsCenter")

       }
}