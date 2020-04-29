package com.dagger.practice.subComponentInjection

import dagger.Module
import dagger.Provides

@Module
class TennisModule constructor(playerName : String) {

      var playerName : String = playerName


       @PerTennisActivity
       @Provides
       fun providePlayerName():String = playerName


       @PerTennisActivity
       @Provides
       fun provideTennis(name : String, sports: Sports) : Tennis = Tennis(name,sports)

}