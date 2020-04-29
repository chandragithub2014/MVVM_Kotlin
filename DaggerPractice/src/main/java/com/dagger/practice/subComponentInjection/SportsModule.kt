package com.dagger.practice.subComponentInjection

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class SportsModule(sportsCenter : String){
    var sportsCenter : String = sportsCenter

     @Singleton
     @Named("sport center")
     @Provides
     fun provideSportCenter(): String = sportsCenter


     @Singleton
     @Provides
     fun provideSports(): Sports = Sports(sportsCenter)

}