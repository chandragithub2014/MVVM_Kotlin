package com.dagger.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dagger.practice.applevelsingletoInjection.AppComponent
import com.dagger.practice.subComponentInjection.Cricket
import com.dagger.practice.subComponentInjection.CricketModule
import com.dagger.practice.subComponentInjection.Sports
import com.dagger.practice.subComponentInjection.SportsComponent
import com.mvvm.appnavigator.openActivity
import kotlinx.android.synthetic.main.activity_cricket.*
import javax.inject.Inject

class CricketActivity : AppCompatActivity() {
    @Inject
    lateinit var sports: Sports

    @Inject
    lateinit var cricket: Cricket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cricket)

        var  sportsComponent : SportsComponent = AppApplication.sportsComponent
       var cricketComponent =  sportsComponent.getCricketComponent(CricketModule("Sachin"))
        cricketComponent.inject(this)
        sports.displaySportsCenterInfo()
        cricket.displayCricketInfo()

        navigate_tennis.setOnClickListener {

            this?.openActivity(Class.forName("com.dagger.practice.TennisActivity"))
        }



    }
}