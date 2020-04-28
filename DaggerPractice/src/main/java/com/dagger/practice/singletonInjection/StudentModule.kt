package com.dagger.practice.singletonInjection

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class StudentModule constructor(driverName:String,studentName : String){

    var driverName:String = driverName
    var studentName:String = studentName

    @Named("driver")
    @Singleton
    @Provides
    fun provideDriverName() : String = driverName

    @Named("student Name")
    @Provides
    fun provideStudentName() : String = studentName


    @Singleton
    @Provides
    fun provideDriver():Driver = Driver(driverName)


    @Provides
    fun provideStudent(driver:Driver):Student = Student(driver,studentName)


}