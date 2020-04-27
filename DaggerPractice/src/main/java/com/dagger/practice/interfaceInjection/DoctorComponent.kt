package com.dagger.practice.interfaceInjection

import com.dagger.practice.InterfaceInjectionActivity
import dagger.Component

@Component (modules = [PrivateDoctorModule::class])

interface DoctorComponent {

    fun inject(interfaceInjectionActivity: InterfaceInjectionActivity)
}