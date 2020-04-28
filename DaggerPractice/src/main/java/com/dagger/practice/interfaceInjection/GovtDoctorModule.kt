package com.dagger.practice.interfaceInjection

import dagger.Module
import dagger.Provides

@Module
class GovtDoctorModule constructor(age:Integer,year : Integer) {

    var age:Integer = age
    var year : Integer = year

     @Provides
     fun provideDoctorAge() : DoctorAge{
         return DoctorAge(age,year)
     }
     @Provides
     fun provideGovtDoctor() : DoctorType{
         return GovtDoctor()
     }

    @Provides
    fun provideDoctor(doctorType: GovtDoctor,doctorAge: DoctorAge) : Doctor{
        return Doctor(doctorType,doctorAge)
    }

}