package com.example.android.topic41.data.database

import com.example.android.topic41.domain.util.Article
import io.reactivex.Single

interface CacheInterface {
    fun getArticlesByTheme(theme: String): Single<List<Article>>
    fun refresh(articles: List<Article>, currentTime: Long)
}
