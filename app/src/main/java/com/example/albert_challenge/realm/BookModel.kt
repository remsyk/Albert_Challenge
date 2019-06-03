package com.example.albert_challenge.realm

import com.example.albert_challenge.model.JSONData
import io.realm.Realm
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

}