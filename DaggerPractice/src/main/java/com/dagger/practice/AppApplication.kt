package com.dagger.practice
import android.app.Application
import android.content.Context
import com.dagger.practice.applevelsingletoInjection.AppComponent
import com.dagger.practice.applevelsingletoInjection.ApplicationModule
import com.dagger.practice.applevelsingletoInjection.DaggerAppComponent
import javax.inject.Inject
import javax.inject.Named

class AppApplication : Application()
 {

    companion object
    {
        var ctx: Context? = null
        lateinit var appComponent: AppComponent

    }

    override fun onCreate()
    {
        super.onCreate()
        ctx = applicationContext
        appComponent = initDaggerComponent()
    }

    fun getMyComponent(): AppComponent
    {
        return appComponent


    }

    private fun initDaggerComponent(): AppComponent
    {
        appComponent = DaggerAppComponent.builder().
        applicationModule(ApplicationModule("John")).
        build()

        return appComponent


    }

 }
