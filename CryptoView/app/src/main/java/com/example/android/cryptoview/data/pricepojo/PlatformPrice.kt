package com.example.android.cryptoview.data.pricepojo

import com.google.gson.annotations.SerializedName


class PlatformPrice {
    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("symbol")
    var symbol: String? = null

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("token_address")
    var tokenAddress: String? = null
}
