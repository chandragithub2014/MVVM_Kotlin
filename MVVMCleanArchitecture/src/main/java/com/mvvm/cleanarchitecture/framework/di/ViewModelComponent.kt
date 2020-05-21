package com.mvvm.cleanarchitecture.framework.di

import com.mvvm.cleanarchitecture.framework.PostListViewModel
import com.mvvm.cleanarchitecture.framework.PostViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class,RepositoryModule::class,UseCasesModule::class])
interface ViewModelComponent {

    fun inject(postViewModel: PostViewModel)
    fun inject(postListViewModel: PostListViewModel)

}