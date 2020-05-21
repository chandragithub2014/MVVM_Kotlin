package com.mvvm.cleanarchitecture.framework.di

import com.mvvm.cleanarchitecture.framework.UseCases
import com.mvvmcore.repository.PostDataRepository
import com.mvvmcore.usecases.*
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {


    @Provides
    fun getUseCases(repository: PostDataRepository)  = UseCases(
        AddPost(repository),
        GetPost(repository),
        GetPosts(repository),
        RemovePost(repository),
        GetPostCount()
        )
}