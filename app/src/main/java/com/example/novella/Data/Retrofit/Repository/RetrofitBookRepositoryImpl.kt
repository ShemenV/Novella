package com.example.novella.Data.Retrofit.Repository

import com.example.novella.Data.Retrofit.Interfaces.BookService
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.Repositories.BookRepository

class RetrofitBookRepositoryImpl(private val bookApi: BookService): BookRepository {
    override suspend fun getBooks(): MutableList<Book?> {
        return bookApi.getBookList("r").items?.map { b -> b?.ToBook()  }?.toMutableList()!!
    }

    override suspend fun getBooksByName(name: String?, startIndex: Int): MutableList<Book?>? {
        return (bookApi.getBookList(name,startIndex).items?.map { b -> b?.ToBook()  } as MutableList<Book?>?)!!
    }
}