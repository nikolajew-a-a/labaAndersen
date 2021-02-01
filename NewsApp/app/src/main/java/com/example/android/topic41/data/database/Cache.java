package com.example.android.topic41.data.database;

import android.os.AsyncTask;
import android.util.Log;


import com.example.android.topic41.domain.util.Article;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


public class Cache implements CacheInterface{
    private ArticlesDatabase database;
    private ArticleDao articleDao;
    private static Cache instance;
    private static final int TIME_TO_REFRESH = 60_000;

    public static Cache getInstance(ArticlesDatabase database, ArticleDao articleDao) {
        if (instance == null) {
            instance = new Cache(database, articleDao);
        }
        return instance;
    }

    public Cache(ArticlesDatabase database, ArticleDao articleDao) {
        this.database = database;
        this.articleDao = articleDao;
    }



    @Override
    public Single<List<Article>> getArticlesByTheme(String theme) {
        return articleDao.getArticlesByTheme(theme).subscribeOn(Schedulers.io());
    }

    @Override
    public void refresh(List<Article> articles, Long currentTime) {
        articleDao.deleteByTheme(articles.get(0).getTheme(), currentTime);
        articleDao.insert(articles);
    }
}
