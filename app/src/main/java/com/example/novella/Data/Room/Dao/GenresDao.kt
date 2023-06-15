package com.example.novella.Data.Room.Dao

import androidx.room.Dao
import androidx.room.Query
import com.example.novella.Data.Room.DbEntities.GenresDbEntity

@Dao
interface GenresDao {
    @Query("SELECT * FROM genres")
    suspend fun getAllGenres(): MutableList<GenresDbEntity>
}