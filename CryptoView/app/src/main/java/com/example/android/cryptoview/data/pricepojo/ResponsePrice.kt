package com.example.android.cryptoview.data.pricepojo

import com.google.gson.annotations.SerializedName

class ResponsePrice {
    @SerializedName("data")
    var cryptoObjectPrices: List<CryptoObjectPrice>? = null

    @SerializedName("status")
    var statusPrice: StatusPrice? = null
}