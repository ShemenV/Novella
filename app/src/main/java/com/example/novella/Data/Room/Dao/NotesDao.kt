package com.example.novella.Data.Room.Dao

import androidx.room.Dao
import androidx.room.Query
import com.example.novella.Data.Room.DbEntities.NoteDbEntity

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): MutableList<NoteDbEntity>
}