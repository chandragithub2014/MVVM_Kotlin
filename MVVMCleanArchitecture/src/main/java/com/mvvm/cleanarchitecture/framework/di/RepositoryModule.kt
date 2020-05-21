package com.mvvm.cleanarchitecture.framework.di

import android.app.Application
import com.mvvm.cleanarchitecture.framework.RoomPostDataSource
import com.mvvmcore.repository.PostDataRepository
import com.mvvmcore.repository.PostDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app : Application) = PostDataRepository(RoomPostDataSource(app))
}