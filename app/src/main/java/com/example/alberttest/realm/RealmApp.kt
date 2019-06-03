package com.example.alberttest.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        var c = RealmConfiguration.Builder(applicationContext)
        c.name("book")
        c.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(c.build())
    }
}