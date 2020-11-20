package com.example.android.cryptoview.data.iconpojo

import com.google.gson.annotations.SerializedName

class CryptoObjectIcon {
    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("symbol")
    var symbol: String? = null

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("logo")
    var logo: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("date_added")
    var dateAdded: String? = null

    @SerializedName("notice")
    var notice: String? = null

    @SerializedName("tags")
    var tags: List<String>? = null

    @SerializedName("platform")
    var platform: PlatformIcon? = null

    @SerializedName("urls")
    var urls: UrlsIcon? = null
}