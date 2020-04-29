package com.dagger.practice.subComponentInjection

import android.util.Log

private const val TAG = "Tennis"
class Tennis constructor(playerName : String , sports : Sports) {
    var playerName : String = playerName
     var sports : Sports = sports
      fun displayTennisInfo(){
          Log.d(TAG,"Tennis Player name is $playerName and its corresponding instance is $this and its corresponding sports instance is $sports")
      }
}