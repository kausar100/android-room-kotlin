package com.kausar.android_room_kotlin.room_utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kausar.android_room_kotlin.room_utils.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}


/*
DAO: maps method calls to database queries, so that when the Repository
 calls a method such as getAlphabetizedWords(),
 Room can execute SELECT * FROM word_table ORDER BY word ASC.

 DAO can expose suspend queries for one-shot requests and
 Flow queries - when you want to be notified of changes in the database.
 */
