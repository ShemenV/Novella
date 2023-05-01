package com.example.novella.Domain.Usecases

import com.example.novella.Domain.Entities.Book
import com.example.novella.Domain.Repositories.BookRepository

class GetReadBooksListUseCase(private val repository: BookRepository) {
    suspend fun execute(): List<Book?>{
        return repository.getBooks()
    }
}