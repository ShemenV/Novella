package com.example.novella.di

import androidx.room.Room
import com.example.novella.Data.Retrofit.Interfaces.BookService
import com.example.novella.Data.Retrofit.Repository.RetrofitBookRepositoryImpl
import com.example.novella.Data.Room.AppDatabase
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.Data.Room.Dao.BooksGenresDao
import com.example.novella.Data.Room.Dao.GenresDao
import com.example.novella.Data.Room.Dao.NotesDao
import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl
import com.example.novella.Data.Room.Repository.RoomBooksGenresRepositoryImpl
import com.example.novella.Data.Room.Repository.RoomGenresRepositoryImpl
import com.example.novella.Data.Room.Repository.RoomNotesRepositoryImpl
import com.example.novella.Data.sharedPreferences.SortParamsRepositoryImpl
import com.example.novella.domain.Repositories.BookRepository
import com.example.novella.domain.Repositories.SortParamsRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var dataModule = module {

    single{
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "novella4.db"
        )
            .createFromAsset("Novella.db")
            .build()
    }

    single<BooksDao> {
        val database = get<AppDatabase>()
        database.getBooksDao()
    }

    single<GenresDao>{
        val database = get<AppDatabase>()
        database.getGenresDao()
    }

    single<BooksGenresDao>
    {  val database = get<AppDatabase>()
        database.getBookGenresDao()
    }

    single<NotesDao>{
        val database = get<AppDatabase>()
        database.getNotesDao()
    }

    single<BookRepository>(named("room")) {
        RoomBookRepositoryImpl(booksDao = get())
    }

    single<BookRepository>(named("retrofit")) {
        RetrofitBookRepositoryImpl(bookApi = get())
    }

    single<RoomBookRepositoryImpl>(named("roomIds")){
        RoomBookRepositoryImpl(booksDao = get())
    }
    single<RetrofitBookRepositoryImpl>() {
        RetrofitBookRepositoryImpl(bookApi = get())
    }

    single<RoomGenresRepositoryImpl>(){
        RoomGenresRepositoryImpl(genresDao = get())
    }

    single<RoomBooksGenresRepositoryImpl>(){
        RoomBooksGenresRepositoryImpl(booksGenresDao = get())
    }

    single<RoomNotesRepositoryImpl>(){
        RoomNotesRepositoryImpl(notesDao = get(), booksDao = get())
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

    single<SortParamsRepository>{
        SortParamsRepositoryImpl(get())
    }

}