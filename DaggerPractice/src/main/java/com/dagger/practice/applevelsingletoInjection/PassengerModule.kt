package com.dagger.practice.applevelsingletoInjection

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class PassengerModule constructor( passengerName:String){

    var passengerName : String = passengerName


    @Provides
    fun providePassengerName():String = passengerName

    @Provides
    fun providesPassenger(driver: Driver,passengerName: String):Passenger = Passenger(driver, passengerName)





    /*   @Named("driver name")
    @Provides
    fun provideDriverName():String = driverName
*/

    /*@Singleton
    @Provides
    fun providesDriver(): Driver = Driver(driverName)*/


}
