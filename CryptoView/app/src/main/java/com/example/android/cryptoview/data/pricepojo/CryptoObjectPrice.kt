package com.example.android.cryptoview.data.pricepojo

import com.google.gson.annotations.SerializedName
import java.util.*


class CryptoObjectPrice {
    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("symbol")
    var symbol: String? = null

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("cmc_rank")
    var cmcRank = 0

    @SerializedName("num_market_pairs")
    var numMarketPairs = 0

    @SerializedName("circulating_supply")
    var circulatingSupply = 0.0

    @SerializedName("total_supply")
    var totalSupply = 0.0

    @SerializedName("market_cap_by_total_supply")
    var marketCapByTotalSupply = 0.0

    @SerializedName("max_supply")
    var maxSupply = 0.0

    @SerializedName("last_updated")
    var lastUpdated: String? = null

    @SerializedName("date_added")
    var dateAdded: String? = null

    @SerializedName("tags")
    var tags: ArrayList<String>? = null

    @SerializedName("platform")
    var platformPrice: PlatformPrice? = null

    @SerializedName("quote")
    var quote: HashMap<String, ValuePrice>? = null
}
