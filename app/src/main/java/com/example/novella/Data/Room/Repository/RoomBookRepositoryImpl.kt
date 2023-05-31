package com.example.novella.Data.Room.Repository

import com.example.novella.Data.Room.Dao.BooksDao
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
}