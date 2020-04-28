package com.dagger.practice.applevelsingletoInjection

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(name:String) {
var name:String = name


    @Singleton
    @Named("driver name")
    @Provides
    fun provideDriverName():String = name

    @Singleton
    @Provides
    fun provideDriver():Driver = Driver(name)
}