package com.example.android.topic41.data.database

import com.example.android.topic41.domain.util.Article
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class Cache(private val database: ArticlesDatabase, private val articleDao: ArticleDao) : CacheInterface {
    override fun getArticlesByTheme(theme: String): Single<List<Article>> {
        return articleDao.getArticlesByTheme(theme).subscribeOn(Schedulers.io())
    }

    override fun refresh(articles: List<Article>, currentTime: Long) {
        articleDao.deleteByTheme(articles[0].theme, currentTime)
        articleDao.insert(articles)
    }
}
