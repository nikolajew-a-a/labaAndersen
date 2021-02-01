package com.example.android.topic41.data.network;



import com.example.android.topic41.domain.util.Article;

import java.util.List;

import io.reactivex.Single;


public interface NetworkInterface {
    Single<List<Article>> createObservable(String theme);
}
