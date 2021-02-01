package com.example.android.topic41.di.components;

import android.app.Activity;

import com.example.android.topic41.di.modules.ArticlesViewModelModule;
import com.example.android.topic41.di.scope.PerActivity;
import com.example.android.topic41.presentation.view.MainActivity;
import com.example.android.topic41.presentation.viewmodel.ArticlesViewModel;

import dagger.BindsInstance;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ArticlesViewModelModule.class})
public interface MainActivityComponent  {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder activity(Activity activity);

        Builder appComponent(AppComponent component);

        MainActivityComponent build();
    }

    void inject(MainActivity activity);
}
