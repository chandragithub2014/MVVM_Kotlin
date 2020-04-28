package com.dagger.practice.singletonInjection

import com.dagger.practice.SingletonInjectionActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StudentModule::class])
interface StudentComponent {

    fun inject(singletonInjectionActivity: SingletonInjectionActivity)
}