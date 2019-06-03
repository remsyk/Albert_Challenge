package com.example.albert_challenge.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        var c = RealmConfiguration.Builder(applicationContext)
        c.name("book_wish_list")
        c.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(c.build())
    }
}