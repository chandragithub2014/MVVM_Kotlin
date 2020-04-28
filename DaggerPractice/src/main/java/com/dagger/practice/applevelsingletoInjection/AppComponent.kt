package com.dagger.practice.applevelsingletoInjection

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ApplicationModule::class])

interface AppComponent {
  //  fun inject(appLevelSingletonActivity: AppLevelSingletonActivity)
   fun getActivityComponent(passengerModule:  PassengerModule): PerActivityComponent
}