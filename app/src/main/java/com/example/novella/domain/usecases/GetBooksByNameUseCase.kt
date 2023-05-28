package com.example.novella.domain.usecases

import com.example.novella.domain.Entities.Book
import com.example.novella.domain.Repositories.BookRepository

class GetBooksByNameUseCase(private val repository:BookRepository) {

    suspend fun execute(name: String?):List<Book?>{
        return repository.getBooksByName(name)
    }
}