package com.example.android.topic41.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.android.topic41.domain.util.Article;

import java.util.List;

import io.reactivex.Single;


@Dao
public interface ArticleDao {
    @Query("SELECT * FROM articles_table WHERE theme = :topic  ORDER BY publishedAt DESC")
    Single<List<Article>> getArticlesByTheme(String topic);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Article> articles);

    @Query("DELETE FROM articles_table WHERE theme = :topic AND time < :currentTime")
    void deleteByTheme(String topic, Long currentTime);
}
