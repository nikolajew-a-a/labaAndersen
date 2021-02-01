package com.example.android.topic41.di.modules;

import com.example.android.topic41.data.repository.ArticlesRepository;
import com.example.android.topic41.domain.usecase.SingleUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SingleUseCaseModule {

    @Singleton
    @Provides
    static SingleUseCase provideSingleUseCase(ArticlesRepository repository){
        return new SingleUseCase(repository);
    }
}
