package com.example.android.cryptoview.presentation.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

class MainActivity : AppCompatActivity(), ItemListener {
    private val cryptoObjects: List<CryptoObject> = ArrayList()
    private lateinit var adapter: Adapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: Repository

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        adapter = Adapter(cryptoObjects, this)
        recyclerView.adapter = adapter

        repository = (application as BaseApplication).repository

        mainViewModel = ViewModelProvider(this, MainViewModelProviderFactory(repository!!))
                .get(MainViewModel::class.java)
        mainViewModel!!.getAllCryptoObjects()
        mainViewModel!!.getListCryptoObject()
                .observe(this, { list: List<CryptoObject?>? -> adapter!!.setCryptoObjects(list) })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favourite_objects -> {
                val intent = Intent(this, FavouriteObjectsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onClickItem(cryptoObject: CryptoObject) {
        if (!cryptoObject.isFavourite!!) {
            mainViewModel!!.writeToDB(cryptoObject.id!!)
        } else if (cryptoObject.isFavourite) {
            mainViewModel!!.deleteFromDB(cryptoObject.id!!)
        }
    }
}