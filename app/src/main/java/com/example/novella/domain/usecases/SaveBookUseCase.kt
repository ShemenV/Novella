package com.example.novella.domain.usecases

import android.util.Log
import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl
import com.example.novella.domain.Entities.Book

class SaveBookUseCase(private val repository:RoomBookRepositoryImpl) {
    suspend fun execute(book: Book?){
        val idsList = repository.getIds()

        if (idsList.contains(book?.id)) {
            Log.e("vvvuivui","hohohohoho")
            if (book != null) {
                repository.updateBook(book)
            }
        }
        else{
            Log.e("vvvuivui","hihihihi")
            if (book != null) {
                repository.saveBook(book)
            }
        }

    }



}