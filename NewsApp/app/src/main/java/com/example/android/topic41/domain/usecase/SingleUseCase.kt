package com.example.android.topic41.domain.usecase

import com.example.android.topic41.data.repository.ArticlesRepositoryInterface
import com.example.android.topic41.domain.util.Article
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class SingleUseCase(private val repository: ArticlesRepositoryInterface) : SingleUseCaseInterface {

    override fun loadArticles(theme: String): Single<List<Article>> {
        return repository.loadArticles(theme)
                .subscribeOn(Schedulers.io())
                .map { articles: List<Article> -> filter(articles) }
    }

    private fun filter(articles: List<Article>): List<Article> {
        return articles.map {
            when (it.title.length > 20) {
                true -> {
                    it.title = "\"Filtered:\" ${it.title}"
                    it
                }
                false -> it
            }
        }
    }
}
