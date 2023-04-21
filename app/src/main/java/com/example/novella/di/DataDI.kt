package com.example.novella.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.novella.Data.Room.AppDatabase
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.Repository.BookRepositoryImpl
import com.example.novella.Domain.Repositories.BookRepository
import com.example.novella.Presentation.Activities.MainActivity
import com.example.novella.Presentation.Fragments.MainFragment
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

var dataModule = module {

    single{
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "novella.db"
        )
            .createFromAsset("Novella.db")
            .build()
    }

    single<BooksDao> {
        val database = get<AppDatabase>()
        database.getBooksDao()
    }

    single<BookRepositoryImpl> {
        BookRepositoryImpl(booksDao = get())
    }



}