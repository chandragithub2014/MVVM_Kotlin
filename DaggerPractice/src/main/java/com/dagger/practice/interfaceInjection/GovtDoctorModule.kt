package com.dagger.practice.interfaceInjection

import dagger.Module
import dagger.Provides

@Module
class GovtDoctorModule constructor(age:Int) {

    var age:Int = age

     @Provides
     fun provideDoctorAge() : DoctorAge{
         return DoctorAge(age)
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