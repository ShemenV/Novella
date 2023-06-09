package com.example.novella.Data.Room.Dao

import androidx.room.*
import com.example.novella.Data.Room.DbEntities.NoteDbEntity
import com.example.novella.domain.Entities.Note

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): MutableList<NoteDbEntity>
    @Insert
    suspend fun saveNote(note: NoteDbEntity)
    @Query("SELECT * FROM notes WHERE Id = :id")
    suspend fun getNoteById(id: Int): NoteDbEntity?
    @Update
    suspend fun updateNote(note: NoteDbEntity)
    @Delete
    suspend fun deleteNote(note: NoteDbEntity)
}