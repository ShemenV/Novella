package com.example.novella.Data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.DbEntities.BooksDbEntity
import com.example.novella.Data.Room.DbEntities.ReadStatusesDbEntity

@Database(
    version = 5,
    entities =[
        BooksDbEntity::class,
        ReadStatusesDbEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getBooksDao(): BooksDao
}