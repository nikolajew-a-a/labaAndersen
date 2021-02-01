package com.example.android.topic41.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android.topic41.domain.util.Article;

@Database(entities = Article.class, version = 1, exportSchema = false)
public abstract class ArticlesDatabase extends RoomDatabase {
    private static final String DB_NAME = "database";
    private static ArticlesDatabase instance;

    public static synchronized ArticlesDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ArticlesDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ArticleDao articleDao();
}
