package com.dagger.practice.applevelsingletoInjection

import com.dagger.practice.AppLevelSingletonActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [PassengerModule::class])
interface PerActivityComponent {

    fun inject(appLevelSingletonActivity: AppLevelSingletonActivity)
}