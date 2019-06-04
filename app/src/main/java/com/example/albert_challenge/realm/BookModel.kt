package com.example.albert_challenge.realm

import com.example.albert_challenge.model.JSONData
import io.realm.Realm
import io.realm.RealmResults


class BookModel : BookInterface {

    //adds book to realm
    override fun addBook(bookList: List<JSONData?>?, pos: Int) {
        Realm.getDefaultInstance().use { r ->
            r.executeTransaction { realm ->
                //constructs BookRealmObject to be placed in database
                val book: BookRealmObject = realm.copyToRealmOrUpdate(BookRealmObject().apply{
                    seed = bookList?.get(pos)?.seed?.get(1).toString()
                    title = bookList?.get(pos)?.title
                    authorName =  bookList?.get(pos)?.authorName?.get(0)
                    coverID = bookList?.get(pos)?.coverI
                })
            }
        }
    }

    //Deletes requested book from Realm database and returns updated list
    override fun delBook(realm: Realm, book:BookRealmObject?) :RealmResults<BookRealmObject> {
        realm.executeTransaction(Realm.Transaction { realm ->
            realm.where( BookRealmObject:: class.java).equalTo("seed", book?.seed).findAll()?.deleteAllFromRealm()
        })
        return getbooks(realm)
    }

    //retrieves all of the books from database
    fun getbooks(realm: Realm): RealmResults<BookRealmObject> {
        return realm.where(BookRealmObject::class.java).findAll()
    }


}




