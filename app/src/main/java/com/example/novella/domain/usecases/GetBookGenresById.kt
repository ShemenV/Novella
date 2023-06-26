package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBooksGenresRepositoryImpl

class GetBookGenresById(private val repository: RoomBooksGenresRepositoryImpl) {
    suspend fun execute(bookId: String): MutableList<Int>{
        return repository.getGenresByBookId(bookId)
    }
}