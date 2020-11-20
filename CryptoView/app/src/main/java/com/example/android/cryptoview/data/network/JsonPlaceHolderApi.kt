package com.example.android.cryptoview.data.network

import com.example.android.cryptoview.data.iconpojo.ResponseIcon
import com.example.android.cryptoview.data.pricepojo.ResponsePrice
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface JsonPlaceHolderApi {
    @GET("/v1/cryptocurrency/listings/latest")
    fun getPrice(@QueryMap parameters: Map<String, String>): Single<ResponsePrice>

    @GET("/v1/cryptocurrency/info")
    fun getIcon(@QueryMap parameters: Map<String, String>): Single<ResponseIcon>
}
