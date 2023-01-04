package com.kausar.android_room_kotlin

import android.app.Application
import com.kausar.android_room_kotlin.room_utils.WordRoomDatabase
import com.kausar.android_room_kotlin.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created
    // when they're needed rather than when the application starts
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}
