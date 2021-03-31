package com.example.seihekijinrou.Start

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

open class realmInit: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true).build()
        Realm.setDefaultConfiguration(config)

    }

}