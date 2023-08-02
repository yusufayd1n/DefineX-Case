package com.example.definexcase

import android.app.Application
import com.example.definexcase.di.appModule
import com.example.definexcase.di.databaseModule
import com.example.definexcase.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, networkModule, databaseModule))
        }
    }
}