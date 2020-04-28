package com.dagger.practice.namedInjection

import com.dagger.practice.NamedInjectionActivity
import dagger.Component

@Component(modules = [MovieModule::class])
interface MovieComponent {

    fun inject(namedInjectionActivity : NamedInjectionActivity)
}