package com.example.android.cryptoview

import android.app.Application
import com.example.android.cryptoview.data.database.Cache
import com.example.android.cryptoview.data.database.FavouriteDatabase.Companion.getDatabase
import com.example.android.cryptoview.data.network.JsonPlaceHolderApi
import com.example.android.cryptoview.data.network.Network
import com.example.android.cryptoview.data.repository.Repository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class BaseApplication : Application() {
    val BASE_URL = "https://pro-api.coinmarketcap.com"
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val network = Network(jsonPlaceHolderApi)
        val database = getDatabase(this)
        val dao = database.favouriteDao()
        val cache = Cache(database, dao)
        repository = Repository(network, cache)
    }
}