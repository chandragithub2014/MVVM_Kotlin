package com.dagger.practice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dagger.practice.applevelsingletoInjection.*
import javax.inject.Inject
import javax.inject.Named

class AppLevelSingletonActivity : AppCompatActivity() {

    @Inject
    lateinit var  passengerName : String


  /*  @Inject
    lateinit var driver: Driver
*/
    @Inject
    lateinit var  passenger: Passenger


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_level_singleton)

        var  appComponent : AppComponent = AppApplication.appComponent
        /*appComponent.inject(this)*/

        val component: PerActivityComponent = /*(application as AppApplication).getMyComponent()*/
            appComponent.getActivityComponent(PassengerModule("Pollock"))
        component.inject(this)
        passenger.displayPassengerInfo()

    }
}