package com.dagger.practice.interfaceInjection

import dagger.Module
import dagger.Provides

@Module
class PrivateDoctorModule constructor(age:Int) {

    var age:Int = age

     @Provides
     fun provideDoctorAge() : DoctorAge{
         //  doctorAge.displayDoctorAge()
         return DoctorAge(age)
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