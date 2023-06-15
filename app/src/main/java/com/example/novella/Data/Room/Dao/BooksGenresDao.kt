package com.example.novella.Data.Room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.novella.Data.Room.DbEntities.BooksGenresDbEntity
import com.example.novella.Data.Room.DbEntities.Tuples.GenreIdTuple

@Dao
interface BooksGenresDao {

    @Insert
    suspend fun addBookGenre(bookGenre: BooksGenresDbEntity)

    @Query("DELETE FROM BooksGenres WHERE BookId = :bookId")
    suspend fun deleteGenresByBookId(bookId: String)

    @Query("SELECT GenreId FROM BooksGenres WHERE BookId = :bookId")
    suspend fun getGenresByBookId(bookId: String): MutableList<GenreIdTuple>
}