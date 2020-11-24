package com.example.android.cryptoview.presentation.view

import com.example.android.cryptoview.data.repository.CryptoObject

interface ItemListener {
    fun onClickItem(cryptoObject: CryptoObject)
}