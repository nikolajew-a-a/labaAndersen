package com.example.android.topic41;

import android.app.Application;

import com.example.android.topic41.di.components.AppComponent;
import com.example.android.topic41.di.components.DaggerAppComponent;

public class BaseApplication extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().application(this).build();
    }
}