package com.example.android.topic41.di.modules

import com.example.android.topic41.data.repository.ArticlesRepository
import com.example.android.topic41.domain.usecase.SingleUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SingleUseCaseModule {
    @Singleton
    @Provides
    fun provideSingleUseCase(repository: ArticlesRepository): SingleUseCase {
        return SingleUseCase(repository)
    }
}