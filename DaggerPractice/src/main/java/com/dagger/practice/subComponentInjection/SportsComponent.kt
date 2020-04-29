package com.dagger.practice.subComponentInjection


import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SportsModule::class])
interface SportsComponent {
    fun getCricketComponent(cricketModule: CricketModule): CricketComponent
    fun getTennisComponent(tennisModule: TennisModule) : TennisComponent
}