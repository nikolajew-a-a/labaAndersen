package com.example.android.topic41.domain.usecase

import com.example.android.topic41.domain.util.Article
import io.reactivex.Single

interface SingleUseCaseInterface {
    fun loadArticles(theme: String): Single<List<Article>>
}
