package com.example.novella.Data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.DbEntities.BooksDbEntity

@Database(
    version = 1,
    entities =[
        BooksDbEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getBooksDao(): BooksDao
}