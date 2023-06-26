package com.example.novella.domain.Repositories

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.novella.Data.Room.DbEntities.NoteDbEntity
import com.example.novella.domain.Entities.Note

interface NotesRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllNotes(): MutableList<Note>

    suspend fun addNewNote(note: Note)


    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}