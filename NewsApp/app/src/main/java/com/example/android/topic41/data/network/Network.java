package com.example.android.topic41.data.network;

import androidx.annotation.NonNull;

import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.domain.util.News;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class Network implements NetworkInterface {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Single<List<Article>> observable;
    private Map<String, String> parameters = new HashMap<>();

    private final String KEY = "apiKey";
    private final String KEY_VALUE = "eb3c938655f74099948faa2b250623df";



    public Network(JsonPlaceHolderApi jsonPlaceHolderApi) {
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
    }

    @Override
    public Single<List<Article>> createObservable(@NonNull String theme) {
        parameters.put(KEY, KEY_VALUE);
        parameters.put("q", theme);
        observable = jsonPlaceHolderApi.getNews(parameters).map(News::getArticles);
        return observable;
    }
}
