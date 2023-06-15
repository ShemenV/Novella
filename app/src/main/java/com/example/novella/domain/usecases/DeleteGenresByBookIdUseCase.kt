package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBooksGenresRepository

class DeleteGenresByBookIdUseCase(private val repository: RoomBooksGenresRepository) {
    suspend fun execute(bookId: String){
        repository.deleteByBookId(bookId)
    }
}