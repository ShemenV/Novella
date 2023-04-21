package com.example.novella.Data.Room.Repository

import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.DbEntities.BooksDbEntity
import com.example.novella.Domain.Entities.Book
import com.example.novella.Domain.Repositories.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val booksDao: BooksDao
    ): BookRepository {
    override fun getBooks(): Flow<Book?> {
        return booksDao.getAllBooks().map { value -> value?.ToBook() }
    }
}