package com.dagger.practice
import android.app.Application
import android.content.Context
import com.dagger.practice.applevelsingletoInjection.AppComponent
import com.dagger.practice.applevelsingletoInjection.ApplicationModule
import com.dagger.practice.applevelsingletoInjection.DaggerAppComponent
import com.dagger.practice.subComponentInjection.DaggerSportsComponent
import com.dagger.practice.subComponentInjection.SportsComponent
import com.dagger.practice.subComponentInjection.SportsModule


class AppApplication : Application()
 {

    companion object
    {
        var ctx: Context? = null
        lateinit var appComponent: AppComponent
        lateinit var sportsComponent : SportsComponent

    }

    override fun onCreate()
    {
        super.onCreate()
        ctx = applicationContext
        appComponent = initDaggerComponent()
        sportsComponent = initSportsComponent()
    }

    fun getMyComponent(): AppComponent
    {
        return appComponent


    }

   private fun initSportsComponent(): SportsComponent{
       sportsComponent = DaggerSportsComponent.builder().sportsModule(SportsModule("Eden")).build()
       return  sportsComponent
   }
    private fun initDaggerComponent(): AppComponent
    {
        appComponent = DaggerAppComponent.builder().
        applicationModule(ApplicationModule("John")).
        build()

        return appComponent


    }

 }
