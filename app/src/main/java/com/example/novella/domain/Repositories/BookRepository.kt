package com.example.novella.domain.Repositories

import com.example.novella.domain.Entities.Book

interface BookRepository {
    suspend fun getBooks(): List<Book?>

    suspend fun getBooksByName(name: String?): List<Book?>
}