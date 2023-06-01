package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl

class GetBooksIdsUseCase(private val repository: RoomBookRepositoryImpl) {

    suspend fun execute(): MutableList<String?>{
        return repository.getIds()
    }

}