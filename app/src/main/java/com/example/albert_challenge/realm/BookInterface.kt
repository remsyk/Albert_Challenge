package com.example.albert_challenge.realm

import com.example.albert_challenge.model.JSONData
import io.realm.Realm

interface BookInterface {
    fun addBook(bookList: List<JSONData?>?, pos: Int)
    fun delBook(bookList: List<JSONData?>?, pos: Int)
}