package com.example.albert_challenge.realm

import io.realm.Realm

interface BookInterface {
    fun addBook(realm: Realm, book: BookRealmObject): Boolean
    fun delBook(realm: Realm, title: String): Boolean
    fun editBook(realm: Realm, book: BookRealmObject): Boolean
    fun getBook(realm: Realm, title: String): BookRealmObject
}