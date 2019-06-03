package com.example.alberttest.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookRealmObject(
    @PrimaryKey
    open var title: String="",
    open var authorName: String = "",
    open var coverID: Int = 8314541,
    open var seed: String = ""
)
    : RealmObject() {

    fun copy(
        title: String = this.title,
        authorName: String = this.authorName,
        coverID: Int= this.coverID,
        seed: String = this.seed )
            = BookRealmObject(title, authorName, coverID, seed )
}