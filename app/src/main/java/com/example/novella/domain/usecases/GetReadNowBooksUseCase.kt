package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl
import com.example.novella.domain.Entities.Book

class GetReadNowBooksUseCase(private val repositoryImpl: RoomBookRepositoryImpl) {
    suspend fun execute():MutableList<Book?>{
        return repositoryImpl.getReadNowBooks()
    }
}