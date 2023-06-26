package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl

class GetBooksCountUseCase(private val repository: RoomBookRepositoryImpl) {
    suspend fun execute(readStatus: Int? = null): Int{
        return if(readStatus == null){
            repository.getAllBooksCount()
        } else{
            repository.getBooksCountByReadStatus(readStatus)
        }
    }
}