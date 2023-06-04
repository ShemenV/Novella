package com.example.novella.domain.usecases

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
            repository.updateBook(book)
        }
        else{
            if (book.cover == null && book.coverUrl != null) {
                val imageUrl: URL = URL(book.coverUrl)

                withContext(Dispatchers.IO) {
                    val urlConnection: URLConnection =imageUrl.openConnection()
                    val inputStream: InputStream = urlConnection.getInputStream()
                    val outputStream: ByteArrayOutputStream = ByteArrayOutputStream()

                    inputStream.use { input ->
                        outputStream.use { output ->
                            input.copyTo(output)
                        }
                    }

                    val byteArray = outputStream.toByteArray()
                    book.cover = byteArray
                }
            }
            repository.saveBook(book)
        }

    }



}