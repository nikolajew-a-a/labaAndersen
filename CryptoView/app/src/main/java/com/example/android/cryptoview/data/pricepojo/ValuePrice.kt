package com.example.android.cryptoview.data.pricepojo

import com.google.gson.annotations.SerializedName

class ValuePrice {
    @SerializedName("price")
    var price = 0.0

    @SerializedName("volume_24h")
    var volume24h = 0.0

    @SerializedName("volume_24h_reported")
    var volume24hReported = 0.0

    @SerializedName("volume_7d")
    var volume7d = 0.0

    @SerializedName("volume_7d_reported")
    var volume7dReported = 0.0

    @SerializedName("volume_30d")
    var volume30d = 0.0

    @SerializedName("volume_30d_reported")
    var volume30dReported = 0.0

    @SerializedName("market_cap")
    var marketCap = 0.0

    @SerializedName("percent_change_1h")
    var percentChange1h = 0.0

    @SerializedName("percent_change_24h")
    var percentChange24h = 0.0

    @SerializedName("percent_change_7d")
    var percentChange7d = 0.0

    @SerializedName("last_updated")
    var lastUpdated: String? = null
}