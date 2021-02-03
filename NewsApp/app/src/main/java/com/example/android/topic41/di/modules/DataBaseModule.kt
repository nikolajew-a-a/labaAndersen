package com.example.android.topic41.di.modules

import android.app.Application
import androidx.room.Room
import com.example.android.topic41.data.database.ArticleDao
import com.example.android.topic41.data.database.ArticlesDatabase
import com.example.android.topic41.data.database.Cache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideNewsDatabase(application: Application): ArticlesDatabase {
        return Room.databaseBuilder(application.applicationContext, ArticlesDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(database: ArticlesDatabase): ArticleDao {
        return database.articleDao()
    }

    @Singleton
    @Provides
    fun provideCache(database: ArticlesDatabase, articleDao: ArticleDao): Cache {
        return Cache(database, articleDao)
    }

    companion object {
        private const val DB_NAME = "database"
    }
}
