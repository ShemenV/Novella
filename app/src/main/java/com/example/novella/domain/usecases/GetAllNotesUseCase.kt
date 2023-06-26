package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomNotesRepositoryImpl
import com.example.novella.domain.Entities.Note

class GetAllNotesUseCase(private val repository: RoomNotesRepositoryImpl) {
    suspend fun execute(): MutableList<Note> {
        return repository.getAllNotes()
    }
}