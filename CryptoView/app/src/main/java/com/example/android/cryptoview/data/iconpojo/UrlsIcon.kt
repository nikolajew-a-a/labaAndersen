package com.example.android.cryptoview.data.iconpojo

import com.google.gson.annotations.SerializedName


class UrlsIcon {
    @SerializedName("website")
    var website: List<String>? = null

    @SerializedName("technical_doc")
    var technicalDoc: List<String>? = null

    @SerializedName("explorer")
    var explorer: List<String>? = null

    @SerializedName("source_code")
    var sourceCode: List<String>? = null

    @SerializedName("message_board")
    var messageBoard: List<String>? = null

    @SerializedName("chat")
    var chat: List<String>? = null

    @SerializedName("announcement")
    var announcement: List<String>? = null

    @SerializedName("reddit")
    var reddit: List<String>? = null

    @SerializedName("twitter")
    var twitter: List<String>? = null
}
