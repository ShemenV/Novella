package com.example.novella.Data.Room.Dao

import androidx.room.*
import com.example.novella.Data.Room.DbEntities.BooksDbEntity
import com.example.novella.Data.Room.DbEntities.Tuples.BookIdTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): MutableList<BooksDbEntity?>

    @Query("SELECT id FROM books")
    suspend fun getAllIds(): MutableList<BookIdTuple?>

    @Insert
    suspend fun saveBook(booksDbEntity: BooksDbEntity)

    @Update
    suspend fun updateBook(booksDbEntity: BooksDbEntity)
    @Delete
    suspend fun deleteBook(book: BooksDbEntity)
}