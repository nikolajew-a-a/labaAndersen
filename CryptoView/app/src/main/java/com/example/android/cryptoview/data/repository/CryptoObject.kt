package com.example.android.cryptoview.data.repository


class CryptoObject (
    val id: Int?,
    val name: String?,
    val symbol: String?,
    val price: Double?,
    val percentChange24h: Double?,
    val percentChange7d: Double?,
    val marketCap: Double?,
    val iconUrl: String?,
    val isFavourite: Boolean?
)