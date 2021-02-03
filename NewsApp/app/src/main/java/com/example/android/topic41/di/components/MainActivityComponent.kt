package com.example.android.topic41.di.components

import android.app.Activity
import com.example.android.topic41.di.modules.ArticlesViewModelModule
import com.example.android.topic41.di.scope.PerActivity
import com.example.android.topic41.presentation.view.MainActivity
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ArticlesViewModelModule::class])
interface MainActivityComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder
        fun appComponent(component: AppComponent): Builder
        fun build(): MainActivityComponent
    }

    fun inject(activity: MainActivity)
}