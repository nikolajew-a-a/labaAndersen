package com.example.android.topic41.domain.usecase;

import com.example.android.topic41.domain.util.Article;

import java.util.List;

import io.reactivex.Single;

public interface SingleUseCaseInterface {
    Single<List<Article>> loadArticles(String theme);
}
