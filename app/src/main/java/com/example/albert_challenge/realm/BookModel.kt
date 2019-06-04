package com.example.albert_challenge.realm

import android.util.Log
import com.example.albert_challenge.model.JSONData
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults

class BookModel : BookInterface {

    override fun addBook(bookList: List<JSONData?>?, pos: Int) {
        Realm.getDefaultInstance().use { r ->
            r.executeTransaction { realm ->
                val book: BookRealmObject = realm.copyToRealmOrUpdate(BookRealmObject().apply{
                    seed = bookList?.get(pos)?.seed?.get(1).toString()
                    title = bookList?.get(pos)?.title
                    authorName =  bookList?.get(pos)?.authorName?.get(0)
                    coverID = bookList?.get(pos)?.coverI
                })
            }
        }
    }

    //chose to use seed get database reference rather than title because there might be duplicates of the same title
    override fun delBook(bookList: List<JSONData?>?, pos: Int) {
      //TODO
    }

    fun getbooks(realm: Realm): RealmResults<BookRealmObject> {
        return realm.where(BookRealmObject::class.java).findAll()
    }

    fun getbooks2(books: RealmResults<BookRealmObject>): RealmResults<BookRealmObject> {
        Log.i("Realm", books.toString())
        return books
    }

    fun start(realm: Realm) {
        Log.i("Realm", "Onchangelistener OK!")
        var myList: RealmResults<BookRealmObject> = realm.where(BookRealmObject::class.java).findAll()
        myList.addChangeListener(RealmChangeListener<RealmResults<BookRealmObject>> {
            Log.i("Realm", "Database Updated")
            getbooks2(myList)
        })
    }
}