package com.rxretrofit.DI

import com.mvvm.common.di.RetroRxModule
import com.rxretrofit.viewmodel.RetroCoroutineViewModel
import com.rxretrofit.viewmodel.RetroCoroutineViewModelFactory
import com.rxretrofit.viewmodel.RetroRXViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroRxModule::class])
interface RetroRxComponent  {
 fun inject(retroRXViewModel: RetroRXViewModel)
 fun inject(retroCoroutineViewModel: RetroCoroutineViewModel)
 fun inject(retroCoroutineViewModelFactory: RetroCoroutineViewModelFactory)

}