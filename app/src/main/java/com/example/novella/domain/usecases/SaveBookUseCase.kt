package com.example.novella.domain.usecases

import android.util.Log
import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl
import com.example.novella.domain.Entities.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class SaveBookUseCase(private val repository:RoomBookRepositoryImpl) {
    suspend fun execute(book: Book){
        val idsList = repository.getIds()

        if (idsList.contains(book.id)) {
            Log.e("vvvuivui","hohohohoho")
            repository.updateBook(book)
        }
        else{
            Log.e("vvvuivui","hihihihi")
            repository.saveBook(book)
        }

    }



}