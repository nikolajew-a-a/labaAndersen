package com.example.android.topic41.data.repository

import com.example.android.topic41.domain.util.Article
import io.reactivex.Single

interface ArticlesRepositoryInterface {
    fun loadArticles(theme: String): Single<List<Article>>
}