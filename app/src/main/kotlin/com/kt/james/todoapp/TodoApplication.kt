package com.kt.james.todoapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 *   author: James
 *   2019/6/1 16:04
 *   version: 1.0
 */

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("realm.todo")
            .encryptionKey(getKey())
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)
    }

    private fun getKey(): ByteArray {
        return byteArrayOf(1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,
            1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4)
    }

}