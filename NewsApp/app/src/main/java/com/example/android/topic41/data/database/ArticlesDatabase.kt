package com.example.android.topic41.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.topic41.domain.util.Article

@Database(entities = arrayOf(Article::class), version = 1, exportSchema = false)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
