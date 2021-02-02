package com.example.android.topic41.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.topic41.domain.usecase.SingleUseCaseInterface

class ViewModelProviderFactory(var useCase: SingleUseCaseInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            ArticlesViewModel::class.java -> ArticlesViewModel(useCase) as T
            else -> throw Exception("Class is not MainViewModel")
        }
    }
}