package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBooksGenresRepository

class AddBookGenresUseCase(private val repository: RoomBooksGenresRepository) {
    suspend fun execute(bookId:String, genreId: Int){
        repository.addBookGenres(bookId, genreId)
    }
}