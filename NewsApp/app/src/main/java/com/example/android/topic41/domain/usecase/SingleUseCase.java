package com.example.android.topic41.domain.usecase;

import android.util.Log;

import com.example.android.topic41.data.repository.ArticlesRepositoryInterface;
import com.example.android.topic41.domain.util.Article;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class SingleUseCase implements SingleUseCaseInterface {
    private ArticlesRepositoryInterface repository;

    public SingleUseCase(ArticlesRepositoryInterface repository) {
        this.repository = repository;
    }


    @Override
    public Single<List<Article>> loadArticles(String theme) {
        return repository.loadArticles(theme).subscribeOn(Schedulers.io()).map(this::filter);
    }

    private List<Article> filter(List<Article> articles) {
        for (Article article : articles) {
            String title = article.getTitle();
            if (title.length() > 20) {
                article.setTitle("\"Filtered:\" " + title);
            }
        }
        return articles;
    }
}
