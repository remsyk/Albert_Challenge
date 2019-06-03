package com.example.albert_challenge.realm

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey

open class BookRealmObject : RealmObject() {
    @PrimaryKey
    var seed: String? = null
    @Index
    var title: String? = null
    var authorName: String? = null
    var coverID: Int? = null

}


