package com.example.novella.di

import androidx.room.Room
import com.example.novella.Data.Retrofit.Interfaces.BookService
import com.example.novella.Data.Retrofit.Repository.RetrofitBookRepositoryImpl
import com.example.novella.Data.Room.AppDatabase
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl
import com.example.novella.Domain.Repositories.BookRepository
import com.example.novella.Presentation.Fragments.ViewModels.MainFragmentViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var dataModule = module {

    single{
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "novella1.db"
        )
            .createFromAsset("Novella.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<BooksDao> {
        val database = get<AppDatabase>()
        database.getBooksDao()
    }

    single<BookRepository> {
        RoomBookRepositoryImpl(booksDao = get())
    }

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

    }

    fun provideBookApi(retrofit: Retrofit): BookService = retrofit.create(BookService::class.java)

    factory { provideRetrofit() }

    single<BookService> { provideBookApi(get()) }

//    single<BookRepository> {
//        RetrofitBookRepositoryImpl(get())
//    }




}