package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBooksGenresRepositoryImpl

class AddBookGenresUseCase(private val repository: RoomBooksGenresRepositoryImpl) {
    suspend fun execute(bookId:String, genreId: Int){
        repository.addBookGenres(bookId, genreId)
    }
}