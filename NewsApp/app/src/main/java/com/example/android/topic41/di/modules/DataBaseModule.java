package com.example.android.topic41.di.modules;

import android.app.Application;

import androidx.room.Room;

import com.example.android.topic41.data.database.ArticleDao;
import com.example.android.topic41.data.database.ArticlesDatabase;
import com.example.android.topic41.data.database.Cache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DataBaseModule {
    private static final String DB_NAME = "database";

    @Singleton
    @Provides
    static ArticlesDatabase provideNewsDatabase(Application application){
        return Room.databaseBuilder(application.getApplicationContext(), ArticlesDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
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
