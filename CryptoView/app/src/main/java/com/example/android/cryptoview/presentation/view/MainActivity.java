package com.example.android.cryptoview.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.TextView;

import com.example.android.cryptoview.R;

import com.example.android.cryptoview.data.network.JsonPlaceHolderApi;
import com.example.android.cryptoview.data.network.Network;
import com.example.android.cryptoview.data.repository.CryptoObject;
import com.example.android.cryptoview.data.repository.Repository;
import com.example.android.cryptoview.presentation.view.adapter.Adapter;
import com.example.android.cryptoview.presentation.viewmodel.MainViewModel;
import com.example.android.cryptoview.presentation.viewmodel.MainViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String BASE_URL = "https://pro-api.coinmarketcap.com";
    private List<CryptoObject> articles = new ArrayList<>();;
    private Adapter adapter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        adapter = new Adapter(articles);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Network network = new Network(jsonPlaceHolderApi);
        Repository repository = new Repository(network);
        MainViewModel mainViewModel = new ViewModelProvider(this, new MainViewModelProviderFactory(repository))
                .get(MainViewModel.class);

        mainViewModel.getCryptoObjectsPrice();

        mainViewModel.getListCryptoObject().observe(this, list -> {
            adapter.setCryptoObjects(list);
        });
    }
}