package com.example.android.topic41.data.network

import com.example.android.topic41.domain.util.Article
import com.example.android.topic41.domain.util.News
import io.reactivex.Single
import java.util.*


class Network(private val jsonPlaceHolderApi: JsonPlaceHolderApi) : NetworkInterface {
    private lateinit var observable: Single<List<Article>>
    private val parameters: MutableMap<String, String> = HashMap()


    override fun createObservable(theme: String): Single<List<Article>> {
        parameters[KEY] = KEY_VALUE
        parameters["q"] = theme
        observable = jsonPlaceHolderApi.getNews(parameters).map(News::articles)
        return observable
    }

    companion object {
        private const val KEY = "apiKey"
        private const val KEY_VALUE = "eb3c938655f74099948faa2b250623df"
    }
}
