package com.example.android.topic41.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.topic41.domain.usecase.SingleUseCaseInterface
import com.example.android.topic41.domain.util.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*


class ArticlesViewModel(useCase: SingleUseCaseInterface) : ViewModel() {
    private val useCase: SingleUseCaseInterface = useCase
    private var isFirstRequest = true
    private var isInitializedActivity = false
    private val temp = MutableLiveData<List<Article>>()
    private val errorMessage = MutableLiveData<String>()
    private val isLoadingState = MutableLiveData(false)
    private val isShowedErrorMessage = MutableLiveData(true)

    @SuppressLint("CheckResult")
    fun loadArticles(theme: String) {
        if (isFirstRequest || isInitializedActivity) {
            isLoadingState.value = true
            useCase.loadArticles(theme)
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({articles: List<Article> ->setArticles(articles) },
                        { e: Throwable -> e.message?.let { setErrorMessage(it) } })
            isFirstRequest = false
        }
        isInitializedActivity = true
    }

    fun errorMessageShowed() {
        isShowedErrorMessage.value = true
    }

    fun activityDestroyed() {
        isInitializedActivity = false
    }

    val articles: LiveData<List<Article>>
        get() = temp

    fun getIsLoadingState(): LiveData<Boolean> {
        return isLoadingState
    }

    val isShownErrorMessage: LiveData<Boolean>
        get() = isShowedErrorMessage

    fun getErrorMessage(): LiveData<String> {
        return errorMessage
    }

    private fun setArticles(articles: List<Article>) {
        temp.value = articles
        isLoadingState.value = false
    }

    private fun setErrorMessage(message: String) {
        errorMessage.value = message
        temp.value = ArrayList()
        isShowedErrorMessage.value = false
        isLoadingState.value = false
    }
}


