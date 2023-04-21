package com.example.novella.Domain.Repositories

import com.example.novella.Domain.Entities.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getBooks(): List<Book?>
}