package com.dagger.practice.subComponentInjection

import com.dagger.practice.CricketActivity
import dagger.Subcomponent

@PerCricketActivity
@Subcomponent (modules =  [CricketModule::class])

interface CricketComponent {
    fun inject(cricketActivity: CricketActivity)
}