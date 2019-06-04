package com.example.albert_challenge.realm

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())
        Log.i("Realm", "Database Created")
    }
}