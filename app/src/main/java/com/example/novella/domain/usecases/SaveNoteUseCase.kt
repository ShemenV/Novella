package com.example.novella.domain.usecases

import android.util.Log
import com.example.novella.Data.Room.Repository.RoomNotesRepository
import com.example.novella.domain.Entities.Note

class SaveNoteUseCase(private val repository: RoomNotesRepository) {
    suspend fun execute(note: Note){
        if(note.id != null){
            repository.updateNote(note)
            return
        }
        repository.addNewNote(note)
    }
}