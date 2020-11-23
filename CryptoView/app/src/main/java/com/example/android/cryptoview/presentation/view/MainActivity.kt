package com.example.android.cryptoview.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cryptoview.BaseApplication
import com.example.android.cryptoview.R
import com.example.android.cryptoview.data.repository.CryptoObject
import com.example.android.cryptoview.data.repository.Repository
import com.example.android.cryptoview.presentation.view.adapter.Adapter
import com.example.android.cryptoview.presentation.viewmodel.MainViewModel
import com.example.android.cryptoview.presentation.viewmodel.MainViewModelProviderFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    private val articles: List<CryptoObject> = ArrayList()
    private lateinit var adapter: Adapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: Repository

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        adapter = Adapter(articles, this)
        recyclerView.adapter = adapter

        repository = (application as BaseApplication).repository

        mainViewModel = ViewModelProvider(this, MainViewModelProviderFactory(repository!!))
                .get(MainViewModel::class.java)
        mainViewModel!!.getCryptoObjectsPrice()
        mainViewModel!!.getListCryptoObject()
                .observe(this, { list: List<CryptoObject?>? -> adapter!!.setCryptoObjects(list) })
    }


    fun onClickItem(cryptoObject: CryptoObject) {
        if (!cryptoObject.isFavourite!!) {
            mainViewModel!!.writeToDB(cryptoObject.id!!)
        } else if (cryptoObject.isFavourite) {
            mainViewModel!!.deleteFromDB(cryptoObject.id!!)
        }
    }
}