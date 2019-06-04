package com.example.albert_challenge.realm

import com.example.albert_challenge.model.JSONData
import io.realm.Realm
import io.realm.RealmResults

interface BookInterface {
    fun addBook(bookList: List<JSONData?>?, pos: Int)
    fun delBook(realm: Realm, book:BookRealmObject?): RealmResults<BookRealmObject>
}