package com.example.android.topic41.di.modules

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.android.topic41.di.scope.PerActivity
import com.example.android.topic41.domain.usecase.SingleUseCase
import com.example.android.topic41.presentation.viewmodel.ArticlesViewModel
import com.example.android.topic41.presentation.viewmodel.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class ArticlesViewModelModule {
    @PerActivity
    @Provides
    fun provideArticlesViewModel(useCase: SingleUseCase, activity: Activity): ArticlesViewModel {
        val owner = activity as ViewModelStoreOwner
        return ViewModelProvider(owner, ViewModelProviderFactory(useCase))
                .get(ArticlesViewModel::class.java)
    }
}