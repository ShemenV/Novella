package com.example.novella.Data.Room.Repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.Dao.NotesDao
import com.example.novella.Data.Room.DbEntities.NoteDbEntity
import com.example.novella.domain.Entities.Note

class RoomNotesRepositoryImpl(private val notesDao: NotesDao, private val booksDao: BooksDao) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllNotes(): MutableList<Note>{

        val notesEntities = notesDao.getAllNotes()
        val notes = notesDao.getAllNotes().map {it.toNote()}.toMutableList()

        for(i in 0 until notesEntities.size){
            notes[i].book = booksDao.getBookById(notesEntities[i].bookId!!).ToBook()
        }
        return notes
    }
    suspend fun addNewNote(note: Note){
        notesDao.saveNote(NoteDbEntity.fromNote(note))
        Log.e("ABOBA",NoteDbEntity.fromNote(note).toString())
    }


    suspend fun updateNote(note: Note){
        notesDao.updateNote(NoteDbEntity.fromNote(note))
    }

    suspend fun deleteNote(note:Note){
        notesDao.deleteNote(NoteDbEntity.fromNote(note))
    }

}