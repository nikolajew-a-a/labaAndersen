package com.example.android.cryptoview.data.iconpojo

import com.google.gson.annotations.SerializedName
import java.util.*

class ResponseIcon {
    @SerializedName("data")
    var cryptoObjectsIcon: HashMap<String, CryptoObjectIcon>? = null

    @SerializedName("status")
    var statusIcon: StatusIcon? = null
}