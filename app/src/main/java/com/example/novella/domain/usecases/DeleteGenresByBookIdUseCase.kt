package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBooksGenresRepositoryImpl

class DeleteGenresByBookIdUseCase(private val repository: RoomBooksGenresRepositoryImpl) {
    suspend fun execute(bookId: String){
        repository.deleteByBookId(bookId)
    }
}