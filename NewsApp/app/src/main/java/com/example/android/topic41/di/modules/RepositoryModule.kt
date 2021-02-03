package com.example.android.topic41.di.modules

import com.example.android.topic41.data.database.Cache
import com.example.android.topic41.data.network.Network
import com.example.android.topic41.data.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataBaseModule::class, NetworkModule::class])
class RepositoryModule {
    @Singleton
    @Provides
    fun provideArticlesRepository(cache: Cache, network: Network): ArticlesRepository {
        return ArticlesRepository(cache, network)
    }
}