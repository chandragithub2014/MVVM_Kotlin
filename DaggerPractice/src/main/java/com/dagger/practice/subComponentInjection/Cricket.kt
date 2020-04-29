package com.dagger.practice.subComponentInjection

import android.util.Log

private const val TAG = "Cricket"
class Cricket constructor(player : String , sports: Sports) {
    var player : String = player
    var sports : Sports = sports
      fun displayCricketInfo(){
          Log.d(TAG,"Name of Cricket Player is :: $player and its corresponding instance is $this Corresponding sports instance is $sports")
      }
}