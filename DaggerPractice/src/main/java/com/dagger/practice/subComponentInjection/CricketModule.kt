package com.dagger.practice.subComponentInjection

import dagger.Module
import dagger.Provides

@Module
class CricketModule(playerName : String) {

       var playerName : String = playerName

        @PerCricketActivity
        @Provides
        fun provideCricketPlayerName():String = playerName


       @PerCricketActivity
       @Provides
       fun provideCricket(player:String , sports: Sports) : Cricket = Cricket(player,sports)

}