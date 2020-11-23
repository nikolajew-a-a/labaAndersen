package com.example.android.cryptoview.data.network

import com.example.android.cryptoview.data.model.icon.ResponseIcon
import com.example.android.cryptoview.data.model.price.ResponsePrice
import com.example.android.cryptoview.data.repository.CryptoObject
import io.reactivex.Single
import java.util.*

class Network(private val jsonPlaceHolderApi: JsonPlaceHolderApi) {
    private val parameters: MutableMap<String, String> = HashMap()
    private val KEY = "CMC_PRO_API_KEY"
    private val KEY_VALUE = "db0e805e-f47d-4cce-bb05-12dac07449f1"

    fun getSinglePrice(): Single<List<CryptoObject>> {
        parameters[KEY] = KEY_VALUE
        return jsonPlaceHolderApi.getPrice(parameters)
                .map { response -> responsePriceToCryptoObject(response) }
    }

    fun getSingleIcon(idString: String): Single<List<CryptoObject>> {
        parameters[KEY] = KEY_VALUE
        parameters["id"] = idString
        return jsonPlaceHolderApi.getIcon(parameters)
                .map { response -> responseIconToCryptoObject(response) }
    }


    private fun responseIconToCryptoObject(response: ResponseIcon) : List<CryptoObject>? {
        return response.data
                ?.map { CryptoObject(it.value.id,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        it.value.logo,
                        null) }
    }

    private fun responsePriceToCryptoObject(response: ResponsePrice) : List<CryptoObject>? {
        return response.data
                ?.map { CryptoObject(it.id,
                        it.name,
                        it.symbol,
                        it.quote?.get("USD")?.price,
                        it.quote?.get("USD")?.percentChange24h,
                        it.quote?.get("USD")?.percentChange7d,
                        it.quote?.get("USD")?.marketCap,
                        null,
                        null) }
    }

}
