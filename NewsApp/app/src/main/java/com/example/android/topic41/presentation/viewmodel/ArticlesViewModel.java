package com.example.android.topic41.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.topic41.domain.usecase.SingleUseCaseInterface;
import com.example.android.topic41.domain.util.Article;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class ArticlesViewModel extends ViewModel {
    private SingleUseCaseInterface useCase;
    private boolean isFirstRequest = true;
    private boolean isInitializedActivity = false;

    private MutableLiveData<List<Article>> temp = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingState = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> isShowedErrorMessage = new MutableLiveData<>(true);

    public ArticlesViewModel(SingleUseCaseInterface useCase) {
        Log.i("mLog_VIEWMODEL", "I am new again.");
        this.useCase = useCase;
    }

    public void loadArticles(String theme) {
        if(isFirstRequest || isInitializedActivity) {
            isLoadingState.setValue(true);
            useCase.loadArticles(theme)
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Article>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(List<Article> articles) {
                            setArticles(articles);
                        }

                        @Override
                        public void onError(Throwable e) {
                            setErrorMessage(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
            isFirstRequest = false;
        }
        isInitializedActivity = true;
    }

    public void errorMessageShowed() {
        isShowedErrorMessage.setValue(true);
    }

    public void activityDestroyed() {
        isInitializedActivity = false;
    }

    public LiveData<List<Article>> getArticles() {
        return temp;
    }
    public LiveData<Boolean> getIsLoadingState() {
        return isLoadingState;
    }
    public LiveData<Boolean> getIsShownErrorMessage() {
        return isShowedErrorMessage;
    }
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }


    public void setArticles(List<Article> articles) {
        temp.setValue(articles);
        isLoadingState.setValue(false);
    }


    public void setErrorMessage(String message) {
        errorMessage.setValue(message);
        temp.setValue(new ArrayList<>());
        isShowedErrorMessage.setValue(false);
        isLoadingState.setValue(false);
    }
}


