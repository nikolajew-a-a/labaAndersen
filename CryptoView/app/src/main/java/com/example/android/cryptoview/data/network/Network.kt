package com.example.android.cryptoview.data.network

import android.util.Log
import com.example.android.cryptoview.data.iconpojo.ResponseIcon
import com.example.android.cryptoview.data.pricepojo.ResponsePrice
import com.example.android.cryptoview.data.repository.CryptoObject
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.util.*

class Network(private val jsonPlaceHolderApi: JsonPlaceHolderApi) : NetworkInterface {
    private val parameters: MutableMap<String, String> = HashMap()
    private val KEY = "CMC_PRO_API_KEY"
    private val KEY_VALUE = "db0e805e-f47d-4cce-bb05-12dac07449f1"

    fun createObservablePrice(theme: String): Single<ResponsePrice> {
        parameters[KEY] = KEY_VALUE
        return jsonPlaceHolderApi.getPrice(parameters).map { p ->
            Log.i("mLog", p.toString())
            return@map p
        }
    }

    fun createObservableIcon(idString: String): Single<List<CryptoObject>> {
        parameters[KEY] = KEY_VALUE
        parameters["id"] = idString
        return jsonPlaceHolderApi.getIcon(parameters)
                .map { obj ->
                    Log.i("mLog", obj.toString())
                    return@map responseIconToCryptoObject(obj)
                }
    }


    private fun responseIconToCryptoObject(responseIcon: ResponseIcon) : List<CryptoObject> {
        var cryptoObjects : MutableList<CryptoObject> = java.util.ArrayList()
        responseIcon.cryptoObjectsIcon?.forEach {
            var currentCryptoObject : CryptoObject = CryptoObject()
            currentCryptoObject.id = it.value.id
            currentCryptoObject.iconUrl = it.value.logo
            cryptoObjects.add(currentCryptoObject)
        }
        return cryptoObjects
    }

}
