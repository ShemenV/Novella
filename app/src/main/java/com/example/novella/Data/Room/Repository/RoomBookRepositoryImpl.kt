package com.example.novella.Data.Room.Repository

import android.util.Log
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.DbEntities.BooksDbEntity
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.Repositories.BookRepository

class RoomBookRepositoryImpl(
    private val booksDao: BooksDao
    ): BookRepository {
    override suspend fun getBooks(): MutableList<Book?> {
        return booksDao.getAllBooks().map { value -> value?.ToBook() }.toMutableList()
    }

    override suspend fun getBooksByName(name: String?, startIndex: Int): MutableList<Book?>? {
        TODO("Not yet implemented")
    }

    suspend fun getIds(): MutableList<String?> {
        return booksDao.getAllIds().map { value -> value?.id.toString() }.toMutableList()
    }

    suspend fun saveBook(book: Book){
        booksDao.saveBook(BooksDbEntity.fromBook(book))
    }

    suspend fun updateBook(book: Book){
        booksDao.updateBook(BooksDbEntity.fromBook(book))
    }
}