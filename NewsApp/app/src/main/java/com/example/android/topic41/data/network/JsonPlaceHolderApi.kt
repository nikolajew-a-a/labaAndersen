package com.example.android.topic41.data.network

import com.example.android.topic41.domain.util.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface JsonPlaceHolderApi {
    @GET("/v2/top-headlines")
    fun getNews(@QueryMap parameters: Map<String, String>): Single<News>
}