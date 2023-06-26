package com.example.novella.Data.Room.Repository

import com.example.novella.Data.Room.Dao.BooksGenresDao
import com.example.novella.Data.Room.DbEntities.BooksGenresDbEntity

class RoomBooksGenresRepositoryImpl(private val booksGenresDao: BooksGenresDao) {

    suspend fun addBookGenres(bookId: String, genreId:Int){
        val bookGenre = BooksGenresDbEntity(bookId = bookId, genreId = genreId)
        booksGenresDao.addBookGenre(bookGenre)
    }

    suspend fun getGenresByBookId(bookId: String): MutableList<Int> {
        return booksGenresDao.getGenresByBookId(bookId).map { it.genreId }.toMutableList()
    }

    suspend fun deleteByBookId(bookId: String){
        booksGenresDao.deleteGenresByBookId(bookId)
    }

}