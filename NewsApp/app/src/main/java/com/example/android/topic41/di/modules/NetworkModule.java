package com.example.android.topic41.di.modules;

import com.example.android.topic41.data.network.JsonPlaceHolderApi;
import com.example.android.topic41.data.network.Network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class NetworkModule {
    private static String BASE_URL = "https://newsapi.org";

    @Singleton
    @Provides
    static Network provideNetwork(JsonPlaceHolderApi jsonPlaceHolderApi){
        return new Network(jsonPlaceHolderApi);
    }

    @Singleton
    @Provides
    static JsonPlaceHolderApi provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(JsonPlaceHolderApi.class);
    }
}
