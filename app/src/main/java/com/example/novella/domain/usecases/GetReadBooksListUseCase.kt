package com.example.novella.domain.usecases

import com.example.novella.domain.Entities.Book
import com.example.novella.domain.Repositories.BookRepository

class GetReadBooksListUseCase(private val repository: BookRepository) {
    suspend fun execute(): List<Book?>{
        return repository.getBooks()
    }
}