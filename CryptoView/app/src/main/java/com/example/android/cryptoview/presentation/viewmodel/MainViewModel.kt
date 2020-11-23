package com.example.android.cryptoview.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.cryptoview.data.repository.CryptoObject
import com.example.android.cryptoview.data.repository.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction

class MainViewModel(private val repository: Repository) : ViewModel() {
    private var listCryptoObject : MutableLiveData<List<CryptoObject>> = MutableLiveData()
    fun getListCryptoObject() : LiveData<List<CryptoObject>> = listCryptoObject


    @SuppressLint("CheckResult")
    fun getCryptoObjectsPrice() {
        repository.getCryptoObjectsPrice()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ single: List<CryptoObject> -> listCryptoObject.value = single })
                { obj: Throwable -> obj.printStackTrace() }
    }

    fun writeToDB(id: Int) {
        repository.writeToDB(id)
        listCryptoObject.value = this.listCryptoObject.value
                ?.map { changeState(it, id, true) }
    }

    fun deleteFromDB(id: Int) {
        repository.deleteFromDB(id)
        listCryptoObject.value = this.listCryptoObject.value
                ?.map { changeState(it, id, false) }
    }

    private fun changeState(it: CryptoObject, id: Int, newState: Boolean): CryptoObject {
        return if (it.id == id) {
            CryptoObject(it.id,
                    it.name,
                    it.symbol,
                    it.price,
                    it.percentChange24h,
                    it.percentChange7d,
                    it.marketCap,
                    it.iconUrl,
                    newState)
        } else {
            it
        }
    }
}