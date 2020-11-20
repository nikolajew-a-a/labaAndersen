package com.example.android.cryptoview.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.android.cryptoview.data.network.Network
import com.example.android.cryptoview.data.pricepojo.ResponsePrice
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

public class Repository {
    private var network : Network
    lateinit var idString : String

    constructor(network: Network) {
        this.network = network
    }

    @SuppressLint("CheckResult")
    fun getCryptoObjectsPrice() : Single<List<CryptoObject>> {
        val price = network.createObservablePrice("s")
                .subscribeOn(Schedulers.io())
                .map { obj -> responsePriceToCryptoObject(obj) }
                .map { obj -> idString = getIdString(obj)
                    return@map obj}
        val icons =  price.flatMap { list -> getIcons(list) }
        return  Single.zip(price, icons, BiFunction<List<CryptoObject>, List<CryptoObject>, List<CryptoObject>> { t1, t2 ->
            return@BiFunction combine(t1, t2)
        })
    }

    private fun combine(t1: List<CryptoObject>, t2: List<CryptoObject>): List<CryptoObject> {
        var result: MutableList<CryptoObject> = ArrayList()
        for (i in t1.indices) {
            var currentCryptoObject: CryptoObject = CryptoObject()
            currentCryptoObject.id = t1[i].id
            currentCryptoObject.name = t1[i].name
            currentCryptoObject.symbol = t1[i].symbol
            currentCryptoObject.price = t1[i].price
            currentCryptoObject.percentChange24h = t1[i].percentChange24h
            currentCryptoObject.percentChange7d = t1[i].percentChange7d
            currentCryptoObject.marketCap = t1[i].marketCap
            currentCryptoObject.iconUrl = iconById(t2, t1[i].id)
            result.add(currentCryptoObject)
        }
        return result
    }

    private fun iconById(list: List<CryptoObject>, id: Int): String? {
        for (i in list.indices) {
            if(list[i].id == id) {
                return list[i].iconUrl
            }
        }
        return null
    }


    private fun getIcons(list: List<CryptoObject>): Single<List<CryptoObject>> {
        val ids = list.joinToString(separator = ",") { it.id.toString() }
        val icons = network.createObservableIcon(ids)
        return icons
    }

    @SuppressLint("CheckResult")
    fun getCryptoObjectsIcon() : Single<List<CryptoObject>> {
        return  network.createObservableIcon("1")
                .subscribeOn(Schedulers.io())
                .map { obj -> Log.i("mLog", obj[0].getIconUrl())
                    return@map obj}
    }

    private fun getIdString(list: List<CryptoObject>): String {
        Log.i("mLog", list.map { obj -> obj.id }.joinToString())
        return list.map { obj -> obj.id }.joinToString()
    }

//    private fun responseIconToCryptoObject(responseIcon: ResponseIcon) : List<CryptoObject> {
//        var cryptoObjects : MutableList<CryptoObject> = java.util.ArrayList()
//        responseIcon.cryptoObjectsIcon?.forEach {
//            var currentCryptoObject : CryptoObject = CryptoObject()
//            currentCryptoObject.id = it.value.id
//            currentCryptoObject.iconUrl = it.value.logo
//            cryptoObjects.add(currentCryptoObject)
//        }
//        return cryptoObjects
//    }

    private fun responsePriceToCryptoObject(responsePrice: ResponsePrice) : List<CryptoObject> {
        var cryptoObjects : MutableList<CryptoObject> = ArrayList()
        responsePrice.cryptoObjectPrices?.forEach {
            var currentCryptoObject : CryptoObject = CryptoObject()
            currentCryptoObject.id = it.id
            currentCryptoObject.name = it.name
            currentCryptoObject.symbol = it.symbol
            currentCryptoObject.price = it.quote?.get("USD")?.price ?: 0.0
            currentCryptoObject.percentChange24h = it.quote?.get("USD")?.percentChange24h ?: 0.0
            currentCryptoObject.percentChange7d = it.quote?.get("USD")?.percentChange7d ?: 0.0
            currentCryptoObject.marketCap = it.quote?.get("USD")?.marketCap ?: 0.0
            cryptoObjects.add(currentCryptoObject)
        }
        return cryptoObjects
    }
}