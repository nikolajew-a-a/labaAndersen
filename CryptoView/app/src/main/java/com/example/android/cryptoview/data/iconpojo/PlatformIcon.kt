package com.example.android.cryptoview.data.iconpojo

import com.google.gson.annotations.SerializedName


class PlatformIcon {
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
