package com.example.android.topic41.data.database;

import android.os.AsyncTask;

import com.example.android.topic41.domain.util.Article;

import java.util.List;

import io.reactivex.Single;

public interface CacheInterface {
    Single<List<Article>> getArticlesByTheme(String theme);

    void refresh(List<Article> articles, Long currentTime);
}
