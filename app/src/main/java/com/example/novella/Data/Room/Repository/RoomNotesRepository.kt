package com.example.novella.Data.Room.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.Dao.NotesDao
import com.example.novella.domain.Entities.Note

class RoomNotesRepository(private val notesDao: NotesDao, private val booksDao: BooksDao) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllNotes(): MutableList<Note>{


        return notesDao.getAllNotes().map {it.toNote()}.toMutableList()
    }

}