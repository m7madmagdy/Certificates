package com.example.certificates.offline

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmConfiguration(context: Context) {
    val realm: Realm by lazy {
        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .name("certificate.realm")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(1)
            .allowWritesOnUiThread(true)
            .build()
        Realm.getInstance(config)
    }
}