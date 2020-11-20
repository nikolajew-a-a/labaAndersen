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

class MainViewModel : ViewModel {
    private var repository : Repository

    private var listCryptoObject : MutableLiveData<List<CryptoObject>> = MutableLiveData()
    fun getListCryptoObject() : LiveData<List<CryptoObject>> = listCryptoObject

    constructor(repository: Repository) {
        this.repository = repository
    }

    @SuppressLint("CheckResult")
    fun getCryptoObjectsPrice() {
        repository.getCryptoObjectsPrice()
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ single: List<CryptoObject> ->
            single.forEach { Log.i("mLog", it.toString())
            listCryptoObject.value = single}
        }) { obj: Throwable -> obj.printStackTrace()
        }

//        repository.getCryptoObjectsIcon()
//                .observeOn(AndroidSchedulers.mainThread()).subscribe({ single: List<CryptoObject> ->
//                    single.forEach { Log.i("mLog", it.toString())
//                        listCryptoObject.value = single}
//                }) { obj: Throwable -> obj.printStackTrace()
//                }
    }

    @SuppressLint("CheckResult")
    fun exp() {
        Observable.zip(
                Observable.just(
                        "Roses", "Sunflowers", "Leaves", "Clouds", "Violets", "Plastics"),
                Observable.just(
                        "Red", "Yellow", "Green", "White or Grey", "Purple"),
                BiFunction<String, String, String> { type, color ->
                    5+5
                    return@BiFunction "$type are $color"
                }
        )
                .subscribe { v -> println("Received: $v") }
    }
}