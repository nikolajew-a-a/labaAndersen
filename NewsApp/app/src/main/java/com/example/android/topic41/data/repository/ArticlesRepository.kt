package com.example.android.topic41.data.repository

import com.example.android.topic41.data.database.CacheInterface
import com.example.android.topic41.data.network.NetworkInterface
import com.example.android.topic41.domain.util.Article
import io.reactivex.Single
import java.util.*


class ArticlesRepository(private val cache: CacheInterface,
                         private val network: NetworkInterface) : ArticlesRepositoryInterface {

    private val cacheTime: MutableMap<String, Long> = HashMap()

    override fun loadArticles(theme: String): Single<List<Article>> {
        val currentTime = System.currentTimeMillis()
        val isCacheFull = cacheTime[theme] != null && currentTime - cacheTime[theme]!! < TIME_TO_REFRESH
        return if (isCacheFull) {
            cache.getArticlesByTheme(theme)
        } else {
            network.createObservable(theme)
                    .map { articles: List<Article> -> prepareForCache(theme, articles) }
                    .map { articles: List<Article> -> refreshCache(articles) }
        }
    }

    private fun prepareForCache(theme: String, articles: List<Article>): List<Article> {
        val currentTime = System.currentTimeMillis()
        cacheTime[theme] = currentTime
        return articles
                .map { it.time = currentTime; it.theme = theme; it }
    }

    private fun refreshCache(articles: List<Article>): List<Article> {
        if (articles.isNotEmpty()) {
            cache.refresh(articles, articles[0].time)
        }
        return articles
    }

    companion object {
        private const val TIME_TO_REFRESH = 60_000
    }
}
