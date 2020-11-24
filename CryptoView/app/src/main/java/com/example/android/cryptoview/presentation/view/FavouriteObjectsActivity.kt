package com.example.android.cryptoview.presentation.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cryptoview.BaseApplication
import com.example.android.cryptoview.R
import com.example.android.cryptoview.data.repository.CryptoObject
import com.example.android.cryptoview.data.repository.Repository
import com.example.android.cryptoview.presentation.view.adapter.Adapter
import com.example.android.cryptoview.presentation.viewmodel.FavouriteObjectsViewModel
import com.example.android.cryptoview.presentation.viewmodel.MainViewModelProviderFactory
import java.util.ArrayList

class FavouriteObjectsActivity : AppCompatActivity(), ItemListener {
    private val cryptoObjects: List<CryptoObject> = ArrayList()
    private lateinit var adapter: Adapter
    private lateinit var favouriteViewModel: FavouriteObjectsViewModel
    private lateinit var repository: Repository

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_objects)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        adapter = Adapter(cryptoObjects, this)
        recyclerView.adapter = adapter

        repository = (application as BaseApplication).repository

        favouriteViewModel = ViewModelProvider(this, MainViewModelProviderFactory(repository!!))
                .get(FavouriteObjectsViewModel::class.java)
        favouriteViewModel!!.getFavouriteCryptoObjects()
        favouriteViewModel!!.getListCryptoObject()
                .observe(this, { list: List<CryptoObject?>? -> adapter!!.setCryptoObjects(list) })
    }

    override fun onClickItem(cryptoObject: CryptoObject) {
        if (!cryptoObject.isFavourite!!) {
            favouriteViewModel!!.writeToDB(cryptoObject.id!!)
        } else if (cryptoObject.isFavourite) {
            favouriteViewModel!!.deleteFromDB(cryptoObject.id!!)
        }
    }
}