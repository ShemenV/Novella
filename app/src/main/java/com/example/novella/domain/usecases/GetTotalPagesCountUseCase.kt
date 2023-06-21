package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl

class GetTotalPagesCountUseCase(private val repositoryImpl: RoomBookRepositoryImpl) {
    suspend fun execute():Int?{
        return repositoryImpl.getTotalBooksCount()
    }
}