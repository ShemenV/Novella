package com.example.novella.Data.Retrofit.Repository

import com.example.novella.Data.Retrofit.Interfaces.BookService
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.Repositories.BookRepository

class RetrofitBookRepositoryImpl(private val bookApi: BookService): BookRepository {
    override suspend fun getBooks(): List<Book?> {
        return bookApi.getBookList("r").items?.map { b -> b?.ToBook()  }!!
    }

    override suspend fun getBooksByName(name: String?): List<Book?> {
        return bookApi.getBookList(name).items?.map { b -> b?.ToBook()  }!!
    }
}