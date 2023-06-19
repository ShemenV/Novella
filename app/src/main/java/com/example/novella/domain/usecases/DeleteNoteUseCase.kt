package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomNotesRepository
import com.example.novella.domain.Entities.Note

class DeleteNoteUseCase(private val repository: RoomNotesRepository) {
    suspend fun execute(note: Note) {
        repository.deleteNote(note)
    }
}