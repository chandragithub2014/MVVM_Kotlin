package com.dagger.practice.FieldInjection

import com.dagger.practice.FieldInjectionActivity
import dagger.Component
import dagger.Module

@Component(modules = [StudentModule::class])
interface StudentComponent {
    fun inject(fieldInjectionActivity: FieldInjectionActivity)
}