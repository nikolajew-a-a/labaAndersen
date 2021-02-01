package com.example.android.topic41.data.repository;

import com.example.android.topic41.domain.util.Article;

import java.util.List;

import io.reactivex.Single;

public interface ArticlesRepositoryInterface {
    Single<List<Article>> loadArticles(String theme);


}
