package com.example.android.cryptoview.data.model.price

import com.google.gson.annotations.SerializedName

class ValuePrice (
    val price : Double?,

    @SerializedName("market_cap")
    val marketCap : Double?,

    @SerializedName("percent_change_1h")
    val percentChange1h : Double?,

    @SerializedName("percent_change_24h")
    val percentChange24h : Double?,

    @SerializedName("percent_change_7d")
    val percentChange7d : Double?,
)