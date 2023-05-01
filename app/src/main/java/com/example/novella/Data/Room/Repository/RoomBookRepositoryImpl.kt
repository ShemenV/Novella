package com.example.novella.Data.Room.Repository

import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Domain.Entities.Book
import com.example.novella.Domain.Repositories.BookRepository

class RoomBookRepositoryImpl(
    private val booksDao: BooksDao
    ): BookRepository {
    override suspend fun getBooks(): List<Book?> {
        return booksDao.getAllBooks().map { value -> value?.ToBook() }
    }
}