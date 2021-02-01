package com.example.android.topic41.di.components;


import android.app.Application;

import com.example.android.topic41.BaseApplication;
import com.example.android.topic41.di.modules.DataBaseModule;
import com.example.android.topic41.di.modules.NetworkModule;
import com.example.android.topic41.di.modules.RepositoryModule;
import com.example.android.topic41.di.modules.SingleUseCaseModule;
import com.example.android.topic41.domain.usecase.SingleUseCase;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


@Singleton
@Component(modules = {SingleUseCaseModule.class, RepositoryModule.class, NetworkModule.class, DataBaseModule.class})
public interface AppComponent {
    SingleUseCase getSingleUseCaseModule();

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(BaseApplication application);
}
