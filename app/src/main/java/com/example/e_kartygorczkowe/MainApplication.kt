package com.example.e_kartygorczkowe

import android.app.Application
import com.google.firebase.FirebaseApp
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}