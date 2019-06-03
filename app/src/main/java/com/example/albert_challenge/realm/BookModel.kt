package com.example.albert_challenge.realm

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

    //chose to use seed get database reference rather than title because there might be duplicates of the same title
    override fun delBook(realm: Realm, seed: String): Boolean {
        try {
            realm.beginTransaction()
            realm.where(BookRealmObject :: class.java).equalTo("seed", seed).findFirst().removeFromRealm()
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

    //chose to use seed get database reference rather than title because there might be duplicates of the same title
    override fun getBook(realm: Realm, seed: String): BookRealmObject {
        return realm.where(BookRealmObject::class.java).equalTo("seed", seed).findFirst()
    }

    fun getLastbook(realm: Realm): BookRealmObject {
        return realm.where(BookRealmObject::class.java).findAll().last()
    }

    fun getbooks(realm: Realm): RealmResults<BookRealmObject> {
        return realm.where(BookRealmObject::class.java).findAll()
    }

}