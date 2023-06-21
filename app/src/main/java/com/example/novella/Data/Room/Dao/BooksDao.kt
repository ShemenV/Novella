package com.example.novella.Data.Room.Dao

import androidx.room.*
import com.example.novella.Data.Room.DbEntities.BooksDbEntity
import com.example.novella.Data.Room.DbEntities.Tuples.BookIdTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Query("SELECT * FROM books WHERE IsDeleted = 0")
    suspend fun getAllBooks(): MutableList<BooksDbEntity?>
    @Query("SELECT id FROM books")
    suspend fun getAllIds(): MutableList<BookIdTuple?>
    @Query("SELECT * FROM books WHERE readStatus = 2")
    suspend fun getReadNowBooks(): MutableList<BooksDbEntity?>
    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun getBookById(bookId: String): BooksDbEntity
    @Insert
    suspend fun saveBook(booksDbEntity: BooksDbEntity)
    @Update
    suspend fun updateBook(booksDbEntity: BooksDbEntity)
    @Query("UPDATE books SET IsDeleted = 1 WHERE Id = :id")
    suspend fun deleteBook(id: String)
    @Query("SELECT COUNT(*) FROM books")
    suspend fun getAllBooksCount(): Int
    @Query("SELECT COUNT(*) FROM books WHERE readStatus = :readStatus")
    suspend fun getBookCountByReadStatus(readStatus: Int): Int
    @Query("SELECT SUM(ReadedPage) FROM books")
    suspend fun getTotalPageCount(): Int

}