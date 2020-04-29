package com.dagger.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dagger.practice.subComponentInjection.*
import javax.inject.Inject

class TennisActivity : AppCompatActivity() {
    @Inject
    lateinit var  sports : Sports
    @Inject
    lateinit var  tennis : Tennis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tennis)

        var  sportsComponent : SportsComponent = AppApplication.sportsComponent
        var tennisComponent  =  sportsComponent.getTennisComponent(TennisModule("Roger"))
        tennisComponent.inject(this)
        sports.displaySportsCenterInfo()
        tennis.displayTennisInfo()



    }
}