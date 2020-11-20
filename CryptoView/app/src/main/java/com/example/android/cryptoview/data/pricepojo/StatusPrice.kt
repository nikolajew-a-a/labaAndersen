package com.example.android.cryptoview.data.pricepojo

import com.google.gson.annotations.SerializedName

class StatusPrice {
    @SerializedName("timestamp")
    var timeStamp: String? = null

    @SerializedName("error_code")
    var errorCode = 0

    @SerializedName("error_message")
    var errorMessage: String? = null

    @SerializedName("elapsed")
    var elapsed = 0

    @SerializedName("credit_count")
    var creditCount = 0
}