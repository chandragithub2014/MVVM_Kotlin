package com.dagger.practice.interfaceInjection

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class PrivateDoctorModule constructor(age:Integer,year:Integer) {

    var age:Integer = age
    var year:Integer = year

    /* @Provides
     @Named("doctor age")
     fun provideDoctorAge() : Integer  {
         //  doctorAge.displayDoctorAge()
         return age
     }

    @Provides
    @Named("doctor year")
    fun provideDoctorYear():Integer {
        return  year
    } */

    @Provides
    fun provideDoctorAge(): DoctorAge {
        return  DoctorAge(age,year)
    }
     @Provides
     fun providePrivateDoctor() : DoctorType{
         return PrivateDoctor()
     }

    @Provides
    fun provideDoctor(doctorType: PrivateDoctor,doctorAge: DoctorAge) : Doctor{
        return Doctor(doctorType,doctorAge)
    }

}