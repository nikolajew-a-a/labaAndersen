package com.example.android.topic41.di.modules;

import com.example.android.topic41.data.database.Cache;
import com.example.android.topic41.data.network.Network;
import com.example.android.topic41.data.repository.ArticlesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DataBaseModule.class, NetworkModule.class})
public abstract class RepositoryModule {

    @Singleton
    @Provides
    static ArticlesRepository provideArticlesRepository(Cache cache, Network network){
        return new ArticlesRepository(cache, network);
    }
}
