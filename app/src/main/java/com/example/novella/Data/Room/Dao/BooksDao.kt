package com.example.novella.Data.Room.Dao

import androidx.room.Dao
import androidx.room.Query
import com.example.novella.Data.Room.DbEntities.BooksDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<BooksDbEntity?>

}