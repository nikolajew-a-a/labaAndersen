package com.example.android.cryptoview.data.repository

import android.annotation.SuppressLint
import com.example.android.cryptoview.data.database.Cache
import com.example.android.cryptoview.data.model.favourite.CryptoObjectFavourite
import com.example.android.cryptoview.data.network.Network
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class Repository(private val network: Network, private val cache: Cache) {

    @SuppressLint("CheckResult")
    fun getCryptoObjectsPrice() : Single<List<CryptoObject>> {
        val price = network.getSinglePrice()
                .subscribeOn(Schedulers.io())
        val icons =  price
                .flatMap { list -> getIcons(list) }
        val favourites = cache.favouriteIds
        return  Single.zip(price, icons, favourites, { prices, icons, favourites -> combine(prices, icons, favourites) })
    }

    private fun combine(prices: List<CryptoObject>, icons: List<CryptoObject>, favourites: MutableList<CryptoObjectFavourite>): List<CryptoObject> {
        return prices.map { CryptoObject(it.id,
                it.name,
                it.symbol,
                it.price,
                it.percentChange24h,
                it.percentChange7d,
                it.marketCap,
                iconById(icons, it.id),
                isFavouriteById(favourites, it.id))}
    }

    private fun isFavouriteById(list: MutableList<CryptoObjectFavourite>, id: Int?): Boolean {
        return list.map { it.favouriteId }.contains(id)
    }

    private fun getIcons(list: List<CryptoObject>): Single<List<CryptoObject>> {
        val ids = list.joinToString(separator = ",") { it.id.toString() }
        return network.getSingleIcon(ids)
    }

    private fun iconById(list: List<CryptoObject>, id: Int?): String? {
        return list.find { it.id == id }?.iconUrl
    }

    fun writeToDB(id: Int) {
        cache.insertId(id)
    }

    fun deleteFromDB(id: Int) {
        cache.deleteId(id)
    }
}