package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl
import com.example.novella.domain.Entities.Book

class DeleteBookUseCase(private val repository: RoomBookRepositoryImpl) {
    suspend fun execute(book: Book){
        repository.deleteBook(book)
    }
}