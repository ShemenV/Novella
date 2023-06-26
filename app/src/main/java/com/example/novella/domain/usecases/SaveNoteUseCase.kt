package com.example.novella.domain.usecases

import com.example.novella.Data.Room.Repository.RoomNotesRepositoryImpl
import com.example.novella.domain.Entities.Note

class SaveNoteUseCase(private val repository: RoomNotesRepositoryImpl) {
    suspend fun execute(note: Note) {
        if (note.id != null) {
            repository.updateNote(note)
            return
        }
        repository.addNewNote(note)
    }
}