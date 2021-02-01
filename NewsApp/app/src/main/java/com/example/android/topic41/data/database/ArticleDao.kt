package com.example.android.topic41.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.topic41.domain.util.Article
import io.reactivex.Single

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles_table WHERE theme = :topic  ORDER BY publishedAt DESC")
    fun getArticlesByTheme(topic: String): Single<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: List<Article>)

    @Query("DELETE FROM articles_table WHERE theme = :topic AND time < :currentTime")
    fun deleteByTheme(topic: String, currentTime: Long)
}