package com.example.android.topic41.data.network;




import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.domain.util.News;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {
    @GET("/v2/top-headlines")
    Single<News> getNews(@QueryMap Map<String, String> parameters);
}
