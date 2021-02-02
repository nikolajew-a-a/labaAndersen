package com.example.android.topic41

import android.app.Application
import com.example.android.topic41.di.components.AppComponent
import com.example.android.topic41.di.components.DaggerAppComponent

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }



    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }
}