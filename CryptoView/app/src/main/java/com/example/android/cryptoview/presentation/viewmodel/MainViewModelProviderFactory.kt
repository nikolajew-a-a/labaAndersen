package com.example.android.cryptoview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.cryptoview.data.repository.Repository


class MainViewModelProviderFactory(var repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == MainViewModel::class.java) {
            MainViewModel(repository) as T
        } else {
            throw Exception("Class is not MainViewModel")
        }
    }
}
