package com.example.alberttest.realm

import io.realm.Realm
import io.realm.RealmResults

class BookModel : BookInterface {

    override fun addBook(realm: Realm, book: BookRealmObject): Boolean {
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(book)
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    override fun delBook(realm: Realm, title: String): Boolean {
        try {
            realm.beginTransaction()
            realm.where(BookRealmObject :: class.java).equalTo("title", title).findFirst().removeFromRealm()
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    override fun editBook(realm: Realm, book: BookRealmObject): Boolean {
        try {
            realm.beginTransaction()
            realm.copyToRealm(book)
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    override fun getBook(realm: Realm, title: String): BookRealmObject {
        return realm.where(BookRealmObject::class.java).equalTo("title", title).findFirst()
    }

    fun getLastbook(realm: Realm): BookRealmObject {
        return realm.where(BookRealmObject::class.java).findAll().last()
    }

    fun getbooks(realm: Realm): RealmResults<BookRealmObject> {
        return realm.where(BookRealmObject::class.java).findAll()
    }

}