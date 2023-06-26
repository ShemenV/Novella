package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomGenresRepositoryImpl
import com.example.novella.domain.Entities.Genre

class GetAllGenresUseCase(private val repository: RoomGenresRepositoryImpl) {
    suspend fun execute(): MutableList<Genre>{
        return repository.getGenres()
    }
}