package com.example.android.topic41.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.topic41.domain.usecase.SingleUseCaseInterface;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {
    SingleUseCaseInterface useCase;

    public ViewModelProviderFactory(SingleUseCaseInterface useCase) {
        super();
        this.useCase = useCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ArticlesViewModel.class) {
            return (T) new ArticlesViewModel(useCase);
        }
        return null;
    }
}
