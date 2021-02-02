package com.example.android.topic41.data.network

import com.example.android.topic41.domain.util.Article
import io.reactivex.Single

interface NetworkInterface {
    fun createObservable(theme: String): Single<List<Article>>
}