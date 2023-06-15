package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBooksGenresRepository

class GetBookGenresById(private val repository: RoomBooksGenresRepository) {
    suspend fun execute(bookId: String): MutableList<Int>{
        return repository.getGenresByBookId(bookId)
    }
}