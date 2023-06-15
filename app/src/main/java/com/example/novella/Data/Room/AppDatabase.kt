package com.example.novella.Data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.Dao.BooksGenresDao
import com.example.novella.Data.Room.Dao.GenresDao
import com.example.novella.Data.Room.DbEntities.BooksDbEntity
import com.example.novella.Data.Room.DbEntities.BooksGenresDbEntity
import com.example.novella.Data.Room.DbEntities.GenresDbEntity
import com.example.novella.Data.Room.DbEntities.ReadStatusesDbEntity

@Database(
    version = 1,
    entities =[
        BooksDbEntity::class,
        ReadStatusesDbEntity::class,
        GenresDbEntity::class,
        BooksGenresDbEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getBooksDao(): BooksDao
    abstract fun getGenresDao(): GenresDao
    abstract fun getBookGenresDao(): BooksGenresDao
}