package com.dagger.practice.subComponentInjection

import com.dagger.practice.TennisActivity
import dagger.Subcomponent

@PerTennisActivity
@Subcomponent(modules = [TennisModule::class])
interface TennisComponent {
    fun inject(tennisActivity: TennisActivity)
}