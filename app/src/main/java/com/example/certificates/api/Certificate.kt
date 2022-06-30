package com.example.certificates.api

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Certificate(
    @PrimaryKey
    var id: Int = 0,
    var name: String? = null,
    var image: String? = null
) : RealmObject()