package com.example.albert_challenge.realm

import com.example.albert_challenge.model.JSONData
import io.realm.Realm
import io.realm.RealmResults

//interface for building BookModel
interface BookInterface {
    fun addBook(bookList: List<JSONData?>?, pos: Int)
    fun delBook(realm: Realm, book:BookRealmObject?): RealmResults<BookRealmObject>
}