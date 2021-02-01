package com.example.android.topic41.di.modules;

import android.app.Application;

import com.example.android.topic41.data.database.ArticleDao;
import com.example.android.topic41.data.database.ArticlesDatabase;
import com.example.android.topic41.data.database.Cache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DataBaseModule {

    @Singleton
    @Provides
    static ArticlesDatabase provideNewsDatabase(Application application){
        return ArticlesDatabase.getInstance(application);
    }

    @Singleton
    @Provides
    static ArticleDao provideArticleDao(ArticlesDatabase database){
        return database.articleDao();
    }

    @Singleton
    @Provides
    static Cache provideCache(ArticlesDatabase database, ArticleDao articleDao){
        return new Cache(database, articleDao);
    }

}
