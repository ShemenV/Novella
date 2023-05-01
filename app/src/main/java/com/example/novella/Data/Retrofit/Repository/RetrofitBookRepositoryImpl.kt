package com.example.novella.Data.Retrofit.Repository

import com.example.novella.Data.Retrofit.Interfaces.BookService
import com.example.novella.Domain.Entities.Book
import com.example.novella.Domain.Repositories.BookRepository

class RetrofitBookRepositoryImpl(private val bookApi: BookService): BookRepository {

    override suspend fun getBooks(): List<Book?> {
        return bookApi.getBookList("r").items?.map { b -> b?.ToBook()  }!!
    }
}