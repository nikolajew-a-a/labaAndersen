package com.example.android.topic41.di.components

import android.app.Application
import com.example.android.topic41.BaseApplication
import com.example.android.topic41.di.modules.DataBaseModule
import com.example.android.topic41.di.modules.NetworkModule
import com.example.android.topic41.di.modules.RepositoryModule
import com.example.android.topic41.di.modules.SingleUseCaseModule
import com.example.android.topic41.domain.usecase.SingleUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SingleUseCaseModule::class, RepositoryModule::class, NetworkModule::class, DataBaseModule::class])
interface AppComponent {
    val singleUseCaseModule: SingleUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: BaseApplication)
}