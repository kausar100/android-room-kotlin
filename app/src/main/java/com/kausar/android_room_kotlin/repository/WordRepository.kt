package com.kausar.android_room_kotlin.repository

import androidx.annotation.WorkerThread
import com.kausar.android_room_kotlin.room_utils.Word
import com.kausar.android_room_kotlin.room_utils.WordDao
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}



/*
Repositories are meant to mediate between different data sources.

Repository: manages one or more data sources.
The Repository exposes methods for the ViewModel to interact with the underlying data provider.
 */