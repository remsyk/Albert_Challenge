package com.example.alberttest.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookRealmObject(
    @PrimaryKey
    open var title: String="",
    open var authorName: String = "",
    open var seed: List<String> = listOf("","","")
)
    : RealmObject() {

    fun copy(
        title: String = this.title,
        authorName: String = this.authorName,
        seed: List<String> = this.seed )
            = BookRealmObject(title, authorName, seed)
}