package com.example.novella.Data.Room.Repository

import com.example.novella.Data.Room.Dao.BooksGenresDao
import com.example.novella.Data.Room.Dao.GenresDao
import com.example.novella.Data.Room.DbEntities.BooksGenresDbEntity
import com.example.novella.domain.Entities.Genre

class RoomGenresRepository( private val genresDao: GenresDao) {
    suspend fun getGenres(): MutableList<Genre>{
        return genresDao.getAllGenres().map { it.toGenre() }.toMutableList()
    }
}