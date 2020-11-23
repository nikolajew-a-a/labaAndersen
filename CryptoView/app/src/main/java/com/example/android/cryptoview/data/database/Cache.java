package com.example.android.cryptoview.data.database;


import android.util.Log;

import com.example.android.cryptoview.data.model.favourite.CryptoObjectFavourite;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Cache{
    private FavouriteDatabase database;
    private FavouriteDao favouriteDao;
    private static Cache instance;

    public Cache(FavouriteDatabase database, FavouriteDao favouriteDao) {
        this.database = database;
        this.favouriteDao = favouriteDao;
    }


    public Single<List<CryptoObjectFavourite>> getIds() {
        return favouriteDao.getIds();
    }


    public void insertId(int id) {
        new Thread() {
            public void run() {
                favouriteDao.insert(new CryptoObjectFavourite(id));
                Log.i("mLog_CACHE", "Insert id: " + id);
            }
        }.start();
    }

    public void deleteId(int id) {
        new Thread() {
            public void run() {
                favouriteDao.deleteById(id);
                Log.i("mLog_CACHE", "Delete id:" + id);
            }
        }.start();
    }

    public Single<List<CryptoObjectFavourite>> getFavouriteIds() {
        return favouriteDao.getIds();
//                .toObservable()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<CryptoObjectFavourite>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<CryptoObjectFavourite> cryptoObjectFavourites) {
//                        for (int i = 0; i < cryptoObjectFavourites.size(); i++){
//                            Log.i("mLog_CACHE", String.valueOf(cryptoObjectFavourites.get(i).getFavouriteId()));
//                        }
//                        Log.i("mLog_CACHE", "Hello");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

}